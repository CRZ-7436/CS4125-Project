package com.ExtVision.RentalSystem.DVD;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class DVDGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemID;
    private String title;
    private String genre;
    private String stateIdentifier; // Stores the state as a string for persistence

    @Transient
    private State state; // Not persisted, managed at runtime

    @Transient
    private StateFactory stateFactory; // Used for creating state instances

    public DVDGame() {
        this.stateIdentifier = "AVAILABLE"; // Set initial state identifier
    }

    public DVDGame(Integer itemID, String title, String genre, StateFactory stateFactory) {
        this.itemID = itemID;
        this.title = title;
        this.genre = genre;
        this.stateFactory = stateFactory;
        this.stateIdentifier = "AVAILABLE"; // Set initial state identifier
        loadState(); // Load the state based on the identifier
    }

    // Getters and setters

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        this.stateIdentifier = state.toString(); // Update the state identifier when state changes
    }

    public String getStateIdentifier() {
        return stateIdentifier;
    }

    public void setStateIdentifier(String stateIdentifier) {
        this.stateIdentifier = stateIdentifier;
        if (this.stateFactory != null) {
            this.state = stateFactory.createState(stateIdentifier);
        }
    }

    public void loadState() {
        if (this.stateFactory != null) {
            this.state = stateFactory.createState(stateIdentifier); // Load the state based on the identifier
        }
    }

    public void markAsRented() {
        state.markAsRented(this);
    }

    public void markAsAvailable() {
        state.markAsAvailable(this);
    }

    // Ensure to inject StateFactory after construction
    public void setStateFactory(StateFactory stateFactory) {
        this.stateFactory = stateFactory;
        if (this.stateIdentifier != null) {
            this.state = stateFactory.createState(this.stateIdentifier);
        }
    }
}
