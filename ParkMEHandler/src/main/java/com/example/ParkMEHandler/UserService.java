package com.example.ParkMEHandler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
        User existingUser = userRepository.findById(Math.toIntExact(userId))
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        updateUserData(existingUser, userDetails);
        return userRepository.save(existingUser);
    }

    private void updateUserData(User existingUser, User userDetails) {
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setParkingDeckBooked(userDetails.getParkingDeckBooked());
        existingUser.setBookTime(userDetails.getBookTime());
        existingUser.setLicensePlateNumber(userDetails.getLicensePlateNumber());
    }

    // Delete a user
    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(Math.toIntExact(userId))
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        userRepository.delete(user);
    }
}

