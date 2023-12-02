package com.ExtVision.RentalSystem.DVD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DVDGameService {   

    private final DVDGameRepository repository;
    private final StateFactory stateFactory;
    private final List<DVDGameObserver> observers = new ArrayList<>();

    @Autowired
    public DVDGameService(DVDGameRepository repository, StateFactory stateFactory) {
        this.repository = repository;
        this.stateFactory = stateFactory;
    }

    public List<DVDGame> getAllGames() {
        return repository.findAll();
    }

    public DVDGame findById(Integer id) {
        return repository.findById(id).orElse(null); // Return the DVDGame or null if not found
    }

    public List<DVDGame> getGamesByGenre(String genre) {
        return repository.findByGenre(genre);
    }

    public List<DVDGame> findAvailableDVDGames() {
        return repository.findByStateIdentifier("AVAILABLE");
    }

    public List<DVDGame> findRentedDVDGames() {
        return repository.findByStateIdentifier("RENTED");
    }

    @Transactional
    public void rentDVDGame(int gameId) {
        // Existing implementation
    }

    @Transactional
    public void returnDVDGame(int gameId) {
        // Existing implementation
    }

    @Transactional
    public void save(DVDGame dvdGame) {
        repository.save(dvdGame);
        notifyObservers(dvdGame); // Notify observers about the change
    }

    @Transactional
    public void updateDVDGame(DVDGame updatedDvdGame) {
        DVDGame existingDvdGame = repository.findById(updatedDvdGame.getItemID())
                                        .orElseThrow(() -> new RuntimeException("DVDGame not found with ID: " + updatedDvdGame.getItemID()));

        // Update the existing DVDGame with new values
        existingDvdGame.setTitle(updatedDvdGame.getTitle());
        existingDvdGame.setGenre(updatedDvdGame.getGenre());
        // ... update other fields as necessary

        repository.save(existingDvdGame);
        notifyObservers(existingDvdGame); // Notify observers about the update
    }

    @Transactional
    public void delete(Integer id) {
        Optional<DVDGame> dvdGame = repository.findById(id);
        dvdGame.ifPresent(game -> {
            repository.deleteById(id);
            notifyObservers(game); // Notify observers about the deletion
        });
    }

    public void registerObserver(DVDGameObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(DVDGame dvdGame) {
        for (DVDGameObserver observer : observers) {
            observer.update(dvdGame);
        }
    }
}

