package com.example.ParkMEHandler.service;

import com.example.ParkMEHandler.Deck;
import com.example.ParkMEHandler.Repo.DeckRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeckService {
    private final DeckRepository deckRepository;
    private final int Maxspots = 15;

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
        return new long[] {
                getCountMDeck(),
                getCountTDeck(),
                getCountNDeck(),
                getCountSDeck(),
                getCountBDeck(),
                getCountGDeck()
        };
    }

    @Transactional
    public long[] checkAndInsertEmail(String column, String email) {
        long[] totalAtEachDeck = getCountOfEachDeck();
        long count = 0;
        switch (column.toLowerCase()) {
            case "m":
                count = totalAtEachDeck[0];
                break;
            case "t":
                count = totalAtEachDeck[1];
                break;
            case "n":
                count = totalAtEachDeck[2];
                break;
            case "s":
                count = totalAtEachDeck[3];
                break;
            case "b":
                count = totalAtEachDeck[4];
                break;
            case "g":
                count = totalAtEachDeck[5];
                break;
            default:
                throw new IllegalArgumentException("Invalid column name: " + column);
        }

        if (count < Maxspots) {
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
                default:
                    throw new IllegalArgumentException("Invalid column name: " + column);
            }
            newDeck.setEmail(email);
            deckRepository.save(newDeck);
            totalAtEachDeck = getCountOfEachDeck();
        }
        return totalAtEachDeck;
    }
}
