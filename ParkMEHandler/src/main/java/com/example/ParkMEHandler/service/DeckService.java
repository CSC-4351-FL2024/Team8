package com.example.ParkMEHandler.service;

import com.example.ParkMEHandler.Deck;
import com.example.ParkMEHandler.Repo.DeckRepository;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeckService {
    private final DeckRepository deckRepository;

    @Autowired
    public DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    public long getCountMDeck() {
        return deckRepository.countByMDeck();
    }

    public long getCountTDeck() {
        return deckRepository.countByTDeck();
    }

    public long getCountNDeck() {
        return deckRepository.countByNDeck();
    }

    public long getCountSDeck() {
        return deckRepository.countBySDeck();
    }

    public long getCountBDeck() {
        return deckRepository.countByBDeck();
    }

    public long getCountGDeck() {
        return deckRepository.countByGDeck();
    }

    public long[] getCountOfEachDeck() {
        long[] counts = new long[6];
        counts[0] = deckRepository.countByMDeck();
        counts[1] = deckRepository.countByTDeck();
        counts[2] = deckRepository.countByNDeck();
        counts[3] = deckRepository.countBySDeck();
        counts[4] = deckRepository.countByBDeck();
        counts[5] = deckRepository.countByGDeck();

        return counts;
    }

    @Transactional
    public boolean checkAndInsertEmail(String column, String email, long threshold) {
        long count = 0;
        switch (column.toLowerCase()) {
            case "m":
                count = deckRepository.countByMDeck();
                break;
            case "t":
                count = deckRepository.countByTDeck();
                break;
            case "n":
                count = deckRepository.countByNDeck();
                break;
            case "s":
                count = deckRepository.countBySDeck();
                break;
            case "b":
                count = deckRepository.countByBDeck();
                break;
            case "g":
                count = deckRepository.countByGDeck();
                break;
            default:
                return false; // Column name is not valid
        }

        if (count < threshold) {
            Deck newDeck = new Deck();
            switch (column.toLowerCase()) {
                case "m":
                    newDeck.setMDeck(email);
                    break;
                case "t":
                    newDeck.setTDeck(email);
                    break;
                case "n":
                    newDeck.setNDeck(email);
                    break;
                case "s":
                    newDeck.setSDeck(email);
                    break;
                case "b":
                    newDeck.setBDeck(email);
                    break;
                case "g":
                    newDeck.setGDeck(email);
                    break;
            }
            deckRepository.save(newDeck);
            return true; // Successfully inserted
        }
        return false; // Count is not below threshold or column was invalid
    }
}
