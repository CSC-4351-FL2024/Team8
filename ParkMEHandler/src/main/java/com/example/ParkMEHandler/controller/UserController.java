package com.example.ParkMEHandler.controller;

import com.example.ParkMEHandler.User;
import com.example.ParkMEHandler.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create a new user
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // Get a single user by ID
    @GetMapping("/{userid}")
    public ResponseEntity<User> getUserById(@PathVariable BigInteger userid) {
        try {
            User user = userService.getUserById(userid);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userid, e);
        }
    }

    // Update a user
    @PutMapping("/{userid}")
    public ResponseEntity<User> updateUser(@PathVariable BigInteger userid, @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(userid, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userid, e);
        }
    }

    // Delete a user
    @DeleteMapping("/{userid}")
    public ResponseEntity<Void> deleteUser(@PathVariable BigInteger userid) {
        try {
            userService.deleteUser(userid);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userid, e);
        }
    }

    @PutMapping("/{userid}/reserve")
    public ResponseEntity<User> reserveParkingDeck(@PathVariable BigInteger userid,
            @RequestBody String parkingDeckBooked) {
        try {
            User user = userService.reserveParkingDeck(userid, parkingDeckBooked);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userid, e);
        }
    }
}
