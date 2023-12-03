
package com.ExtVision.RentalSystem.Customer;

// Java's built-in ArrayList class will be used for list operations.
import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.sqm.StrictJpaComplianceViolation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



// This is the main class for Customer in the rental system
@Entity
public class CustomerClass implements CustomerClassInterface {
    // Attributes of the customer
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer customerID;
    private String name;
    private String address;
    private double phoneNum;
    private String email;
    
    private String accountStatus;
    private List<Integer> currentRentals; // List to keep track of current rentals on customers account
    private float balance; // Customer's balance for renting

    // Constructor to initialize a Customer object
    public CustomerClass() {

    }
    public CustomerClass(Integer customerID, String name, String address, double phoneNum, String email){
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.email = email;
        this.accountStatus = "ACTIVE";
        
    }

    // Business Logic Methods
    
    // Method to handle renting an item
    
    // Method to handle returning an item this is being implemented one at a time here until rent logic is complete
    // Method To allow bulk returns won't be implemented yet as I don't want to lock myself to a template just yet

    // Accessor methods (getters) for each attribute

    // Simple enough just used for accessing stored member variables.
    // Not sure if my commenting here is excessive or not but added it for clarity in presentation
    
    // Returns the customer ID
    
    @Override
    public Integer getCustomerID() {
        return customerID;
    }
    
    // Returns the customer's name
    
    @Override
    public String getName() {
        return name;
    }
    
    // Returns the customer's address
    
    @Override
    public String getAddress() {
        return address;
    }
    
    // Returns the customer's phone number
  
    @Override
    public double getPhone() {
        return phoneNum;
    }
    
    // Returns the customer's email
   
    @Override
    public String getEmail() {
        return email;
    }
    
    // Returns the customer's account status
   
    @Override
    public String getAccountStatus() {
        return accountStatus;
    }
    
    // Returns the list of current rentals for the customer
   
    @Override
    public List<Integer> getCurrentRentals() {
        return currentRentals;
    }
    
    // Returns the customer's balance
    
    @Override
    public float getBalance() {
        return balance;
    }
    
    // Mutator methods (setters) for attributes that should be changeable
    
    // Foundation of the Mutator methods, this is just some basic values is not completed yet 
    
    @Override
    public void setCustomerID(Integer customerId){
        this.customerID = customerId;
    }
    
    // Updates the account status
    @Override
    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
    
    // Updates the balance
    @Override
    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public void setName(String name){
        this.name = name;
    }
    @Override
    public void setAddress(String address){
        this.address = address;
    }
    @Override
    public void setphoneNum(double phoneNum){
        this.phoneNum = phoneNum;
    }
    @Override
    public void setEmail(String email){
        this.email = email;
    }
    
    // Validation methods
    
    // More validation checks need to be added currently this is the only one just so we can easily create customer classes
    // They will be added as needed
    // Validates the email format using a regular format
    @Override
    public boolean isValidEmail(String email) {
        // Simple regex to check email format, can be improved for more stringent checks not prority right now
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    
    // Update profile method
    
    // Updates the customer's profile information
    // This method might need to be inside the clerk class but I added here so Customer objects aren't set 
    @Override
    public void updateProfile(String name, String address, double phone, String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.name = name;
        this.address = address;
        this.phoneNum = phone;
        this.email = email;
    }
}
    
    //observer method
    /* 
    @Override
    public void update(Observer observer, int itemID, State state) {
        if (currentRentals.contains(itemID)) {
            try {
                //add endpoint for returning success message
            }
            catch (Exception e) {
                //add endpoint for error message and log exception
            }
        }
    }
}
*/