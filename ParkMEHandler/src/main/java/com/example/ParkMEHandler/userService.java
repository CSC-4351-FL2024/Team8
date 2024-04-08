package com.example.ParkMEHandler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    @Transactional
    public User createUser(User user) {
        // Here you can add additional logic before saving the user
        return userRepository.save(user);
    }

    // Retrieve all users
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Retrieve a single user by ID
    @Transactional(readOnly = true)
    public Optional<User> getUserById(Integer userId) {
        // Additional logic can be added here if needed
        return userRepository.findById(Long.valueOf(userId));
    }

    // Update a user
    @Transactional
    public User updateUser(Integer userId, User userDetails) {
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        // Update user details here
        user.setUserName(userDetails.getUserName());
        user.setEmail_id(userDetails.getEmail_id());
        user.setBookTime(userDetails.getBookTime());
        user.setPlateNumber(userDetails.getPlateNumber());

        return userRepository.save(user);
    }

    // Delete a user
    @Transactional
    public void deleteUser(Integer userId) {
        userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        userRepository.deleteById(Long.valueOf(userId));
    }
}
