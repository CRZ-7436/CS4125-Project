package com.ExtVision.RentalSystem.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String home() {
        return "index"; // This should match the name of your HTML file in 'src/main/resources/templates'
    }

    @GetMapping("/dvdgames")
    public String dvdGames() {
        // Add any model attributes required by dvdgames.html here
        return "dvdgames"; // Returns the dvdgames.html template
    }
} 
