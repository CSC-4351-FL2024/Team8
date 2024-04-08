package com.example.ParkMEHandler;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Custom query method to find users by their username
    List<User> findByUserName(String userName);

    // If you have any other specific queries, you can define them here as well
}

