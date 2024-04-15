package com.example.ParkMEHandler.Repo;

import com.example.ParkMEHandler.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    List<User> findByParkingDeckBooked(String parkingDeckBooked);

    List<User> findByLicensePlateNumber(String licensePlateNumber);

    Optional<User> findByEmailAndLicensePlateNumber(String email, String licensePlateNumber);

    @Modifying
//    @Transactional
    @Query("UPDATE User u SET u.parkingDeckBooked = NULL, u.bookTime = NULL")
        void clearParkingInformation();
}
