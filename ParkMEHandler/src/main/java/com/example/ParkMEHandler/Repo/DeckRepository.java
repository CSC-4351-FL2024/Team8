package com.example.ParkMEHandler.Repo;

import com.example.ParkMEHandler.Deck;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeckRepository extends CrudRepository<Deck, String> {
    // Custom queries to count non-null rows for each column
    @Query("SELECT COUNT(d.mDeck) FROM Deck d")
    long countByMDeck();

    @Query("SELECT COUNT(d.tDeck) FROM Deck d")
    long countByTDeck();

    @Query("SELECT COUNT(d.nDeck) FROM Deck d")
    long countByNDeck();

    @Query("SELECT COUNT(d.sDeck) FROM Deck d")
    long countBySDeck();

    @Query("SELECT COUNT(d.bDeck) FROM Deck d")
    long countByBDeck();

    @Query("SELECT COUNT(d.gDeck) FROM Deck d")
    long countByGDeck();
}
