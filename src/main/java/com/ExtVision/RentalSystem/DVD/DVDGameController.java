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
        model.addAttribute("dvdGames", dvdGameService.getAllGames());
        model.addAttribute("dvdGame", new DVDGame());
        return "dvdgames";
    }

    @PostMapping
    public String addOrUpdateDVDGame(@ModelAttribute DVDGame dvdGame) {
        dvdGameService.save(dvdGame);
        return "redirect:/dvdgames";
    }

    @GetMapping("/delete/{id}")
    public String deleteDVDGame(@PathVariable Integer id) {
        dvdGameService.delete(id);
        return "redirect:/dvdgames";
    }

    @Override
    public void update(DVDGame dvdGame) {
        // Implement logic to handle updates
        // For example, logging the change, updating a view, etc.
    }
}
