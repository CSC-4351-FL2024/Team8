package com.example.ParkMEHandler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Retrieve all users
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Retrieve a single user by ID
    @Transactional(readOnly = true)
    public User getUserById(Long userId) {
        return userRepository.findById(Math.toIntExact(userId))
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
    }

    // Update a user
    @Transactional
    public User updateUser(Long userId, User userDetails) {
        User user = userRepository.findById(Math.toIntExact(userId))
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        // Update user details here
        // Assuming setUserEmail and other setters are correctly named based on your User entity
        user.setUserName(userDetails.getUserName());
        user.setEmail_id(userDetails.getEmail_id()); // Adjusted based on standard naming conventions
        user.setBookTime(userDetails.getBookTime());
        user.setPlateNumber(userDetails.getPlateNumber());

        return userRepository.save(user);
    }

    // Delete a user
    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(Math.toIntExact(userId))
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        userRepository.deleteById(Math.toIntExact(userId));
    }
}
