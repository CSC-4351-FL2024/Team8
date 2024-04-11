package com.example.ParkMEHandler;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "users", schema = "parkmeschema")
public class User {

    @Id
    @Column(name = "userid")
    private BigInteger userId;

    @Column(name = "email")
    private String email;

    @Column(name = "licenseplatenumber")
    private String licensePlateNumber;

    @Column(name = "parkingdeckbooked")
    private String parkingDeckBooked;

    @Column(name = "booktime")
    private Date bookTime;

    // Constructors, getters, and setters

    // Constructors
    public User() {
    }

    public User(BigInteger userId, String email, String parkingDeckBooked, Date bookTime,
            String licensePlateNumber) {
        this.userId = userId;
        this.email = email;
        this.parkingDeckBooked = parkingDeckBooked;
        this.bookTime = bookTime;
        this.licensePlateNumber = licensePlateNumber;
    }

    // Getters
    public BigInteger getUserId() {
        return (userId);
    }

    public String getEmail() {
        return email;
    }

    public String getParkingDeckBooked() {
        return parkingDeckBooked;
    }

    public Date getBookTime() {
        return bookTime;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    // Setters
    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setParkingDeckBooked(String parkingDeckBooked) {
        this.parkingDeckBooked = parkingDeckBooked;
    }

    public void setBookTime(Date bookTime) {
        this.bookTime = bookTime;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }
}
