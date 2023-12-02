package com.ExtVision.RentalSystem.DVD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dvdgames")
public class DVDGameController implements DVDGameObserver {

    private final DVDGameService dvdGameService;

    @Autowired
    public DVDGameController(DVDGameService dvdGameService) {
        this.dvdGameService = dvdGameService;
        dvdGameService.registerObserver(this); // Register as an observer
    }

    @GetMapping
    public String listDVDGames(Model model) {
        model.addAttribute("availableDvdGames", dvdGameService.findAvailableDVDGames());
        model.addAttribute("rentedDvdGames", dvdGameService.findRentedDVDGames()); // Assuming you have this method in your service
        model.addAttribute("dvdGame", new DVDGame());
        return "dvdgames";
    }

    @PostMapping("/add")
    public String addDVDGame(@ModelAttribute DVDGame dvdGame) {
        dvdGameService.save(dvdGame);
        return "redirect:/dvdgames";
    }

    @PostMapping("/update")
    public String updateDVDGame(@ModelAttribute DVDGame dvdGame) {
        dvdGameService.updateDVDGame(dvdGame);
        return "redirect:/dvdgames";
    }

    @GetMapping("/delete/{id}")
    public String deleteDVDGame(@PathVariable Integer id) {
        dvdGameService.delete(id);
        return "redirect:/dvdgames";
    }

    // Add endpoints for renting and returning DVD games
    @GetMapping("/rent/{id}")
    public String rentDVDGame(@PathVariable Integer id) {
        dvdGameService.rentDVDGame(id);
        return "redirect:/dvdgames";
    }

    @GetMapping("/return/{id}")
    public String returnDVDGame(@PathVariable Integer id) {
        dvdGameService.returnDVDGame(id);
        return "redirect:/dvdgames";
    }

    @Override
    public void update(DVDGame dvdGame) {
        // Implement logic to handle updates
        // For example, logging the change, updating a view, etc.
    }
}
