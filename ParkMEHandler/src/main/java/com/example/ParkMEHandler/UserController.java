package com.example.ParkMEHandler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService; // Corrected to use a UserService class

    // Create a new user
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    // Get all users
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get a single user by ID
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId, e);
        }
    }

    // Update a user
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Integer userId, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(Long.valueOf(userId), userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete a user
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(Long.valueOf(userId));
        return ResponseEntity.ok().build();
    }
}

