package com.example.ParkMEHandler;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    private String parkingDeckBooked;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime bookTime;

    private String licensePlateNumber;

    // Constructors
    public User() {
    }

    public User(Long userId, String email, String parkingDeckBooked, LocalDateTime bookTime, String licensePlateNumber) {
        this.userId=userId;
        this.email = email;
        this.parkingDeckBooked = parkingDeckBooked;
        this.bookTime = bookTime;
        this.licensePlateNumber = licensePlateNumber;
    }

    // Getters
    public Long getUserId() {
        return (userId);
    }

    public String getEmail() {
        return email;
    }

    public String getParkingDeckBooked() {
        return parkingDeckBooked;
    }

    public LocalDateTime getBookTime() {
        return bookTime;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    // Setters
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setParkingDeckBooked(String parkingDeckBooked) {
        this.parkingDeckBooked = parkingDeckBooked;
    }

    public void setBookTime(LocalDateTime bookTime) {
        this.bookTime = bookTime;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }
}

