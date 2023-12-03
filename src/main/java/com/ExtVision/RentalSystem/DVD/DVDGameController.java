package com.ExtVision.RentalSystem.DVD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dvdgames")
public class DVDGameController {

    private final DVDGameService dvdGameService;

    @Autowired
    public DVDGameController(DVDGameService dvdGameService) {
        this.dvdGameService = dvdGameService;
    }

    // Get all DVD games
    @GetMapping
    public ResponseEntity<List<DVDGame>> getAllGames() {
        return ResponseEntity.ok(dvdGameService.getAllGames());
    }

    // Get DVD games by genre
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<DVDGame>> getGamesByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(dvdGameService.getGamesByGenre(genre));
    }

    // Rent a DVD game
    @PostMapping("/rent/{gameId}")
    public ResponseEntity<String> rentDVDGame(@PathVariable int gameId) {
        try {
            dvdGameService.rentDVDGame(gameId);
            return ResponseEntity.ok("Game rented successfully.");
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Return a DVD game
    @PostMapping("/return/{gameId}")
    public ResponseEntity<String> returnDVDGame(@PathVariable int gameId) {
        try {
            dvdGameService.returnDVDGame(gameId);
            return ResponseEntity.ok("Game returned successfully.");
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Find available DVD games
    @GetMapping("/available")
    public ResponseEntity<List<DVDGame>> findAvailableDVDGames() {
        return ResponseEntity.ok(dvdGameService.findAvailableDVDGames());
    }
}
