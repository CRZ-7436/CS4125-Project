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
    }

    @GetMapping
    public String listDVDGames(Model model) {
        model.addAttribute("dvdGames", dvdGameService.getAllGames()); // Changed to getAllGames()
        model.addAttribute("dvdGame", new DVDGame());
        return "dvdgames"; // Name of the Thymeleaf template
    }

    @PostMapping
    public String addOrUpdateDVDGame(@ModelAttribute DVDGame dvdGame) {
        dvdGameService.save(dvdGame); // Ensure save() is implemented in DVDGameService
        return "redirect:/dvdgames";
    }

    @GetMapping("/delete/{id}")
    public String deleteDVDGame(@PathVariable Integer id) {
        dvdGameService.delete(id); // Ensure delete(Long id) is implemented in DVDGameService
        return "redirect:/dvdgames";
    }

    @Override
    public void update(DVDGame dvdGame) {
        // Implement your observer update logic here
    }
}
