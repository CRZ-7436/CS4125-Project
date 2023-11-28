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
        Optional<DVDGame> gameOpt = repository.findById(gameId);
        if (gameOpt.isPresent()) {
            DVDGame game = gameOpt.get();
            if ("AVAILABLE".equals(game.getStateIdentifier())) {
                game.setState(stateFactory.createRentedState());
                repository.save(game);
            } else {
                throw new IllegalStateException("Game is not available for rent.");
            }
        } else {
            throw new IllegalArgumentException("Game not found with ID: " + gameId);
        }
    }

    @Transactional
    public void returnDVDGame(int gameId) {
        Optional<DVDGame> gameOpt = repository.findById(gameId);
        if (gameOpt.isPresent()) {
            DVDGame game = gameOpt.get();
            if ("RENTED".equals(game.getStateIdentifier())) {
                game.setState(stateFactory.createAvailableState());
                repository.save(game);
            } else {
                throw new IllegalStateException("Game is not rented.");
            }
        } else {
            throw new IllegalArgumentException("Game not found with ID: " + gameId);
        }
    }
}

