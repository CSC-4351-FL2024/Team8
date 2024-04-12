package com.example.ParkMEHandler.service;

import java.util.Date;
import com.example.ParkMEHandler.User;
import com.example.ParkMEHandler.Repo.UserRepository;
import jakarta.persistence.EntityExistsException;
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
        // Check if the user already exists based on the email
        if (userRepository.existsById(user.getEmail())) {
            throw new EntityExistsException("User already exists with email: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    // Retrieve all users
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Retrieve a single user by email
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userRepository.findById(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }

    // Update a user by email
    @Transactional
    public User updateUser(String email, User userDetails) {
        User existingUser = getUserByEmail(email);

        // Note: No need to set the email on the existing user, it's already the
        // identifier and cannot be changed
        existingUser.setParkingDeckBooked(userDetails.getParkingDeckBooked());
        existingUser.setBookTime(userDetails.getBookTime());
        existingUser.setLicensePlateNumber(userDetails.getLicensePlateNumber());
        return userRepository.save(existingUser);
    }

    // Delete a user by email
    @Transactional
    public void deleteUser(String email) {
        if (!userRepository.existsById(email)) {
            throw new EntityNotFoundException("User not found with email: " + email);
        }
        userRepository.deleteById(email);
    }

    // Reserve a parking deck for the user identified by email
    public User reserveParkingDeck(String email, String parkingDeckBooked) {
        User existingUser = getUserByEmail(email);
        existingUser.setParkingDeckBooked(parkingDeckBooked);
        Date bookTime = new Date();
        existingUser.setBookTime(bookTime);
        return userRepository.save(existingUser);
    }
}
