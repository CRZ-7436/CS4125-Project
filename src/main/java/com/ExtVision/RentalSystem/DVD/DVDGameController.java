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
        dvdGameService.registerObserver(this);
    }

    @GetMapping
    public String listDVDGames(Model model) {
        model.addAttribute("availableDvdGames", dvdGameService.findAvailableDVDGames());
        model.addAttribute("rentedDvdGames", dvdGameService.findRentedDVDGames());
        model.addAttribute("dvdGame", new DVDGame());
        return "dvdgames";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        DVDGame dvdGame = dvdGameService.findById(id);
        model.addAttribute("dvdGame", dvdGame);
        model.addAttribute("availableDvdGames", dvdGameService.findAvailableDVDGames());
        model.addAttribute("rentedDvdGames", dvdGameService.findRentedDVDGames());
        return "dvdgames";
    }

    @PostMapping("/add")
    public String addOrUpdateDVDGame(@ModelAttribute DVDGame dvdGame) {
        if (dvdGame.getItemID() != null && dvdGame.getItemID() > 0) {
            dvdGameService.updateDVDGame(dvdGame);
        } else {
            dvdGameService.save(dvdGame);
        }
        return "redirect:/dvdgames";
    }

    @GetMapping("/delete/{id}")
    public String deleteDVDGame(@PathVariable Integer id) {
        dvdGameService.delete(id);
        return "redirect:/dvdgames";
    }

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
    }
}