package com.example.ParkMEHandler.Repo;

import java.util.List;
import java.util.Optional;

import com.example.ParkMEHandler.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface UserRepository extends JpaRepository<User, Integer> {
        // Existing custom query to find users by their username
//        List<User> findByUserName(String userName);

        // Add additional queries based on user attributes
        Optional<User> findByEmail(String email);

        List<User> findByParkingDeckBooked(String parkingDeckBooked);

        List<User> findByLicensePlateNumber(String licensePlateNumber);

        // Example of a query using a combination of attributes
        Optional<User> findByEmailAndLicensePlateNumber(String email, String licensePlateNumber);
    }


    // If you have any other specific queries, you can define them here as well


