
package com.example.ParkMEHandler.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ParkMEHandler.Repo.UserRepository;
import com.example.ParkMEHandler.Repo.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ResetService {
    private final UserRepository userRepository;
    private final DeckRepository deckRepository;

    @Autowired
    public ResetService(UserRepository userRepository, DeckRepository deckRepository) {
        this.userRepository = userRepository;
        this.deckRepository = deckRepository;
    }

    // runs everyday at 12am
    @Scheduled(cron = "0 50 8 * * ?", zone = "America/New_York")
    @Transactional
        public void resetUserBookingsAndDecks() {
            System.out.println("function called");
            deckRepository.deleteAll(); // delete all rows in deck table
            userRepository.clearParkingInformation();
            System.out.println("Finished");
        }
    }
