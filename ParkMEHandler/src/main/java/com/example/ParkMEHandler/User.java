package com.example.ParkMEHandler;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "users", schema = "parkmeschema")
public class User {

    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "licenseplatenumber")
    private String licensePlateNumber;

    @Column(name = "parkingdeckbooked")
    private String parkingDeckBooked;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "booktime")
    private Date bookTime;

    // Constructors, getters, and setters

    // Constructors
    public User() {
    }

    public User(String email, String parkingDeckBooked, Date bookTime, String licensePlateNumber) {
        this.email = email;
        this.parkingDeckBooked = parkingDeckBooked;
        this.bookTime = bookTime;
        this.licensePlateNumber = licensePlateNumber;
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParkingDeckBooked() {
        return parkingDeckBooked;
    }

    public void setParkingDeckBooked(String parkingDeckBooked) {
        this.parkingDeckBooked = parkingDeckBooked;
    }

    public Date getBookTime() {
        return bookTime;
    }

    public void setBookTime(Date bookTime) {
        this.bookTime = bookTime;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }
}
