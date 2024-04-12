package com.example.ParkMEHandler;

public class ResponseBuilder {
    private User user;
    private long[] deckSpots;

    public ResponseBuilder(User user, long[] deckSpots) {
        this.user = user;
        this.deckSpots = deckSpots;
    }

    // Getters and setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long[] getDeckSpots() {
        return deckSpots;
    }

    public void setDeckSpots(long[] deckSpots) {
        this.deckSpots = deckSpots;
    }
}
