package com.example.ParkMEHandler.Repo;

import com.example.ParkMEHandler.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DeckRepository extends JpaRepository<User, String> {

}