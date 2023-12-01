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

    public List<DVDGame> getGamesByGenre(String genre) {
        return repository.findByGenre(genre);
    }

    public List<DVDGame> findAvailableDVDGames() {
        return repository.findByStateIdentifier("AVAILABLE");
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

    public void notifyObservers(DVDGame dvdGame) {
        for (DVDGameObserver observer : observers) {
            observer.update(dvdGame);
        }
    }
}
