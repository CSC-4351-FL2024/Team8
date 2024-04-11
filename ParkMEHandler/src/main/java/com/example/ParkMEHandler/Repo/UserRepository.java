package com.example.ParkMEHandler.Repo;

import com.example.ParkMEHandler.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email); // This might be redundant now.

    List<User> findByParkingDeckBooked(String parkingDeckBooked);

    List<User> findByLicensePlateNumber(String licensePlateNumber);

    Optional<User> findByEmailAndLicensePlateNumber(String email, String licensePlateNumber);
}
