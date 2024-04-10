package com.example.ParkMEHandler.Repo;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.example.ParkMEHandler.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, BigInteger> {
    Optional<User> findByEmail(String email);

    List<User> findByParkingDeckBooked(String parkingDeckBooked);

    List<User> findByLicensePlateNumber(String licensePlateNumber);

    Optional<User> findByEmailAndLicensePlateNumber(String email, String licensePlateNumber);
}
