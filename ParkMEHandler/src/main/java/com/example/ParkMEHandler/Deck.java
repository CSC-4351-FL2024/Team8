package com.example.ParkMEHandler;

import jakarta.persistence.*;

@Entity
@Table(name = "decks", schema = "parkmeschema")
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Suitable for databases with auto-increment feature, like
                                                        // PostgreSQL's BIGSERIAL
    @Column(name = "id", nullable = false)
    private Long id; // Primary key

    @Column(name = "m")
    private String mDeck;

    @Column(name = "t")
    private String tDeck;

    @Column(name = "n")
    private String nDeck;

    @Column(name = "s")
    private String sDeck;

    @Column(name = "b")
    private String bDeck;

    @Column(name = "g")
    private String gDeck;

    public Deck() {
    }

    // Constructors
    public Deck(String mDeck, String tDeck, String nDeck, String sDeck, String bDeck, String gDeck) {
        this.mDeck = mDeck;
        this.tDeck = tDeck;
        this.nDeck = nDeck;
        this.sDeck = sDeck;
        this.bDeck = bDeck;
        this.gDeck = gDeck;
    }

    // Getter and setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Existing getters and setters
    public String getMDeck() {
        return mDeck;
    }

    public void setMDeck(String mDeck) {
        this.mDeck = mDeck;
    }

    public String getTDeck() {
        return tDeck;
    }

    public void setTDeck(String tDeck) {
        this.tDeck = tDeck;
    }

    public String getNDeck() {
        return nDeck;
    }

    public void setNDeck(String nDeck) {
        this.nDeck = nDeck;
    }

    public String getSDeck() {
        return sDeck;
    }

    public void setSDeck(String sDeck) {
        this.sDeck = sDeck;
    }

    public String getBDeck() {
        return bDeck;
    }

    public void setBDeck(String bDeck) {
        this.bDeck = bDeck;
    }

    public String getGDeck() {
        return gDeck;
    }

    public void setGDeck(String gDeck) {
        this.gDeck = gDeck;
    }
}

