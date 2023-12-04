package com.ExtVision.RentalSystem.Controllers;

import com.ExtVision.RentalSystem.Customer.CustomerClass;
import com.ExtVision.RentalSystem.Customer.CustomerFactory;
import com.ExtVision.RentalSystem.Customer.CustomerService;
import com.ExtVision.RentalSystem.Customer.CustomerServiceImpl;
import com.ExtVision.RentalSystem.DVD.DVDGame;
import com.ExtVision.RentalSystem.LoginFunc.LoginClassInterface;
import com.ExtVision.RentalSystem.LoginFunc.LoginStateFactory.LoginState;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class registerController {

    private final LoginClassInterface loginClass;
    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    public registerController(LoginClassInterface loginClass) {
        this.loginClass = loginClass;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Name of the login HTML page
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        String loginResult = loginClass.login(username, password);

        if (loginResult.equals(LoginState.LOGGED_IN)) {
            // Redirect to a logged-in page or dashboard
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", loginResult);
            return "login";
        }
    }

    @GetMapping("/registerCustomer")
    public String showRegistrationForm(Model model) {
        if (!model.containsAttribute("customer")) {
            model.addAttribute("customer", new CustomerClass());
        }
        return "register";
    }


    @PostMapping("/registerCustomer/register")
    public String register(@RequestParam String username,
                        @RequestParam Integer accountID, 
                       @RequestParam String password,
                       @RequestParam String address,
                       @RequestParam double phoneNum,
                       @RequestParam String email,
                       @RequestParam(defaultValue = "false") boolean admin, 
                       Model model) {
    // Existing logic to register account with username and password
        String registerResult = loginClass.registerAccount(username, password, admin);
                        Integer accountId = loginClass.generateCustomerID();
                        CustomerClass newCustomer = CustomerFactory.createCustomer(accountId, username, address,  phoneNum,  email);
        if (registerResult.equals(LoginState.LOGGED_IN)) {
            // Create a CustomerClass object
            // Implement this method to generate unique IDs
            customerServiceImpl.save(newCustomer);

            return "redirect:/index";
        } else {
        model.addAttribute("error", registerResult);
        return "register";
        }
}

    @PostMapping("/registerCustomer/add")
public String addOrUpdateCustomer(@ModelAttribute CustomerClass customer, RedirectAttributes redirectAttributes) {
    try {
        if (customer != null) {
            if (customer.getaccountId() != null && customer.getaccountId() > 0) {
                // Update existing customer
                customerServiceImpl.updateCustomer(customer);
                redirectAttributes.addFlashAttribute("message", "Customer updated successfully.");
            } else {
                // Save new customer
                customerServiceImpl.save(customer);
                redirectAttributes.addFlashAttribute("message", "Customer added successfully.");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Customer data is not valid.");
            return "redirect:/registerCustomer";
        }
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", "An error occurred: " + e.getMessage());
        return "redirect:/registerCustomer";
    }
    return "redirect:/index";
}

    @PostMapping("/logout")
    public String logout(@RequestParam String username) {
        loginClass.logout(username);
        return "redirect:/login";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String username, @RequestParam String oldPassword,
                                @RequestParam String newPassword, Model model) {
        String resetResult = loginClass.resetPassword(username, oldPassword, newPassword);

        if (resetResult.equals(LoginState.PASSWORD_RESET_SUCCESSFUL)) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", resetResult);
            return "reset-password"; // Name of the reset-password HTML page
        }
    }

}
