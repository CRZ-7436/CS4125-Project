package com.ExtVision.RentalSystem.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String showIndex() {
        return "index"; // Refers to 'index.html' in the 'templates' directory
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm() {
        return "reset-password"; // Refers to 'reset-password.html' in the 'templates' directory
    }

}
