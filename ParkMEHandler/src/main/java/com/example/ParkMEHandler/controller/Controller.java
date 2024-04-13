package com.example.ParkMEHandler.controller;

import com.example.ParkMEHandler.service.DeckService;
import com.example.ParkMEHandler.ResponseBuilder;
import com.example.ParkMEHandler.User;
import com.example.ParkMEHandler.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/users")
public class Controller {
    private final DeckService deckService;
    private final UserService userService;

    @Autowired
    public Controller(UserService userService, DeckService deckService) {
        this.userService = userService;
        this.deckService = deckService;
    }

    // Create a new user
    @PostMapping("/")
    public ResponseEntity<ResponseBuilder> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);

        ResponseBuilder response = new ResponseBuilder(newUser, deckService.getCountOfEachDeck());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get a single user by email
    @GetMapping("/{email}")
    public ResponseEntity<ResponseBuilder> getUserByEmail(@PathVariable String email) {
        try {
            User user = userService.getUserByEmail(email);

            ResponseBuilder response = new ResponseBuilder(user, deckService.getCountOfEachDeck());

            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: " + email, e);
        }
    }

    // Update a user by email
    @PutMapping("/{email}")
    public ResponseEntity<ResponseBuilder> updateUser(@PathVariable String email, @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(email, userDetails);
            ResponseBuilder response = new ResponseBuilder(updatedUser, deckService.getCountOfEachDeck());
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: " + email, e);
        }
    }

    // Delete a user by email
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        try {
            userService.deleteUser(email);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: " + email, e);
        }
    }

    // Reserve a parking deck for a user identified by email
    @PutMapping("/{email}/reserve")
    public ResponseEntity<ResponseBuilder> reserveParkingDeck(@PathVariable String email,
            @RequestBody String parkingDeckBooked) {
        try {
            User user = userService.reserveParkingDeck(email, parkingDeckBooked);
            ResponseBuilder response = new ResponseBuilder(user,
                    deckService.checkAndInsertEmail(parkingDeckBooked, user.getEmail()));
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: " + email, e);
        }
    }

    @DeleteMapping("/{email}/checkout")
    public ResponseEntity<ResponseBuilder> checkoutParkingDeck(@PathVariable String email) {
        try {
            User user = userService.checkoutParkingDeck(email);
            ResponseBuilder response = new ResponseBuilder(user, deckService.checkout(email));
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: " + email, e);
        }
    }

}
