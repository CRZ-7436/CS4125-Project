
package com.ExtVision.RentalSystem.Customer;

// Java's built-in ArrayList class will be used for list operations.
import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.sqm.StrictJpaComplianceViolation;

import com.ExtVision.RentalSystem.LoginFunc.LoginClass;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



// This is the main class for Customer in the rental system
@Entity
public class CustomerClass extends LoginClass implements CustomerClassInterface {
    // Attributes of the customer
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
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
    public CustomerClass(Integer accountId, String name, String address, double phoneNum, String email){
        this.accountId = accountId;
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.email = email;
        this.accountStatus = "ACTIVE";
        
    }

    
    @Override
    public Integer getaccountId() {
        return accountId;
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
    public double getPhoneNum() {
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
    public void setaccountId(Integer accountId){
        this.accountId = accountId;
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
    

    @Override
    public boolean isValidEmail(String email) {

        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    
    // Update profile method
    
    // Updates the customer's profile information

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
    public void setActive(boolean b) {
        this.active = b;
    }
}
    
    //observer method
/*  
    @Override
    public void update(CustomerObserver observer, int itemID, State state) {
        if (currentRentals.contains(itemID)) {
            try {
                //add endpoint for returning success message
            }
            catch (Exception e) {
                //add endpoint for error message and log exception
            }
        }
    }

*/