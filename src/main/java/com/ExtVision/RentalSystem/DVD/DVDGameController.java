package com.ExtVision.RentalSystem.DVD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dvdgames")
public class DVDGameController implements DVDGameObserver {

    // Assume you have a service or repository for DVDGame operations
    private final DVDGameService dvdGameService;

    public DVDGameController(DVDGameService dvdGameService) {
        this.dvdGameService = dvdGameService;
    }

    @GetMapping
    public String listDVDGames(Model model) {
        model.addAttribute("dvdGames", dvdGameService.findAll());
        model.addAttribute("dvdGame", new DVDGame());
        return "dvdgames";
    }

    @PostMapping
    public String addOrUpdateDVDGame(@ModelAttribute DVDGame dvdGame) {
        dvdGameService.save(dvdGame);
        return "redirect:/dvdgames";
    }

    @GetMapping("/delete/{id}")
    public String deleteDVDGame(@PathVariable Long id) {
        dvdGameService.delete(id);
        return "redirect:/dvdgames";
    }

    @Override
    public void update(DVDGame dvdGame) {
        // Implement your observer update logic here
    }
}