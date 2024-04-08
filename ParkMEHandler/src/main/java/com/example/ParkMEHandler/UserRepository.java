package com.example.ParkMEHandler;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ParkMEHandler.User;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByPublished(boolean published);

    List<User> findByTitleContaining(String title);
}
