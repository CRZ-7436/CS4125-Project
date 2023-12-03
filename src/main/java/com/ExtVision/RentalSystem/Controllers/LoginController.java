package com.ExtVision.RentalSystem.Controllers;

import com.ExtVision.RentalSystem.Customer.CustomerClass;
import com.ExtVision.RentalSystem.Customer.CustomerFactory;
import com.ExtVision.RentalSystem.Customer.CustomerService;
import com.ExtVision.RentalSystem.Customer.CustomerServiceImpl;
import com.ExtVision.RentalSystem.DVD.DVDGame;
import com.ExtVision.RentalSystem.LoginFunc.LoginClass;
import com.ExtVision.RentalSystem.LoginFunc.LoginStateFactory.LoginState;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final LoginClass loginClass;
    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    public LoginController(LoginClass loginClass) {
        this.loginClass = loginClass;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Name of the login HTML page
    }

    @GetMapping("/listCustomers")
    public String listCustomers(Model model) {
        model.addAttribute("availableCustomers", customerServiceImpl.findActiveCustomers());
        model.addAttribute("InactiveCustomers", customerServiceImpl.findInActiveCustomers());
        model.addAttribute("customer", new CustomerClass());
        return "/register";
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

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new CustomerClass()); // Assuming CustomerClass is your model class
        return "register";
    }


    @PostMapping("/register")
    public String register(@RequestParam String username, 
                       @RequestParam String password,
                       @RequestParam String address,
                       @RequestParam double phoneNum,
                       @RequestParam String email,
                       @RequestParam(defaultValue = "false") boolean admin, 
                       Model model) {
    // Existing logic to register account with username and password
        String registerResult = loginClass.registerAccount(username, password, admin);

        if (registerResult.equals(LoginState.LOGGED_IN)) {
            // Create a CustomerClass object
            // Implement this method to generate unique IDs
            Integer customerID = loginClass.generateCustomerID();
            CustomerClass newCustomer = CustomerFactory.createCustomer(customerID, username, address,  phoneNum,  email);

            customerServiceImpl.save(newCustomer);

            return "redirect:/index";
        } else {
        model.addAttribute("error", registerResult);
        return "register";
        }
}

    @PostMapping("/registerCustomer/add")
    public String addOrUpdateCustomer(@ModelAttribute CustomerClass customer) {
        if (customer.getCustomerID() != null && customer.getCustomerID() > 0) {
            customerServiceImpl.updateCustomer(customer);
        } else {
            customerServiceImpl.save(customer);
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


    // Additional methods and logic...
}
