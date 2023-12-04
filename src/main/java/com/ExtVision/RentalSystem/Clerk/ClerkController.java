package com.ExtVision.RentalSystem.Clerk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clerk")
public class ClerkController {

    private final ClerkService clerkService;

    @Autowired
    public ClerkController(ClerkService clerkService) {
        this.clerkService = clerkService;
    }

    @GetMapping
    public String getClerkMenu() {
        return "clerk";
    }
    
}
