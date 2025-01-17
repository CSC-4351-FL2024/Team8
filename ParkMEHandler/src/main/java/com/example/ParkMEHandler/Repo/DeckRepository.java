package com.example.ParkMEHandler.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ParkMEHandler.Deck;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;

@Repository
public interface DeckRepository extends JpaRepository<Deck, String> {
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

  @Modifying
  // @Transactional
  void deleteAllInBatch();
}
