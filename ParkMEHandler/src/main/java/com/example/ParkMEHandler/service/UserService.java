package com.example.ParkMEHandler.service;

import java.util.Date;
import com.example.ParkMEHandler.User;
import com.example.ParkMEHandler.Repo.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Timestamp;
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
        // Check if the user already exists based on a unique attribute, e.g., email
        Boolean userExists = userRepository.existsById(user.getUserId());
        if (userExists) {
            throw new EntityExistsException("User already exists with id: " + user.getUserId());
        }
        return userRepository.save(user);
    }

    // Retrieve all users
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Retrieve a single user by ID
    @Transactional(readOnly = true)
    public User getUserById(BigInteger userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
    }

    // Update a user
    @Transactional
    public User updateUser(BigInteger userId, User userDetails) {
        User existingUser = getUserById(userId);

        updateUserData(existingUser, userDetails);
        return userRepository.save(existingUser);
    }

    private void updateUserData(User existingUser, User userDetails) {
        existingUser.setUserId(userDetails.getUserId());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setParkingDeckBooked(userDetails.getParkingDeckBooked());
        existingUser.setBookTime(userDetails.getBookTime());
        existingUser.setLicensePlateNumber(userDetails.getLicensePlateNumber());
    }

    // Delete a user
    @Transactional
    public void deleteUser(BigInteger userId) {
        User existingUser = getUserById(userId);
        userRepository.delete(existingUser);
    }

    public User reserveParkingDeck(BigInteger userId, String parkingDeckBooked) {
        User existingUser = getUserById(userId);

        existingUser.setParkingDeckBooked(parkingDeckBooked);
        Date bookTime = new Date();
        existingUser.setBookTime(bookTime);
        return userRepository.save(existingUser);
    }
}
