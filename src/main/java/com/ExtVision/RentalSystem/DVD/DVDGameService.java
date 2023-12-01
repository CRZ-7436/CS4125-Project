package com.ExtVision.RentalSystem.DVD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DVDGameService {   

    private final DVDGameRepository repository;
    private final StateFactory stateFactory;

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

    // New method to save or update a DVDGame
    @Transactional
    public void save(DVDGame dvdGame) {
        repository.save(dvdGame);
    }

    // New method to delete a DVDGame by id
    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
