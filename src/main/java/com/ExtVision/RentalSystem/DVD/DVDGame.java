package com.ExtVision.RentalSystem.DVD;

import java.util.ArrayList;

import com.ExtVision.RentalSystem.Observer.Observer;
import com.ExtVision.RentalSystem.Observer.Subject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class DVDGame implements Subject{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemID;
    private String title;
    private String genre;
    private String stateIdentifier; // Stores the state as a string for persistence
    private ArrayList<Observer> observers = new ArrayList<>(); // stores observers

    @Transient
    private State state; // Not persisted, managed at runtime

    @Transient
    private StateFactory stateFactory; // Used for creating state instances

    public DVDGame() {
        // Default constructor for JPA
    }

    public DVDGame(int itemID, String title, String genre, StateFactory stateFactory) {
        this.itemID = itemID;
        this.title = title;
        this.genre = genre;
        this.stateFactory = stateFactory;
        this.stateIdentifier = "AVAILABLE"; // Set initial state identifier
        loadState(); // Load the state based on the identifier
    }

    // Getters and setters

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
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

    public void loadState() {
        this.state = stateFactory.createState(stateIdentifier); // Load the state based on the identifier
    }

    // methods needed for Subject class

    @Override
    public void markAsRented() {
        state.markAsRented(this);
    }

    @Override
    public void markAsAvailable() {
        state.markAsAvailable(this);
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer o: observers) {
            o.update(itemID, state);
        }
    }
}
