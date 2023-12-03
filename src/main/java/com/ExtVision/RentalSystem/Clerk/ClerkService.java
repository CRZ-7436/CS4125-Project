package com.ExtVision.RentalSystem.Clerk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ExtVision.RentalSystem.Customer.CustomerClass;
import com.ExtVision.RentalSystem.Customer.CustomerFactory;
import com.ExtVision.RentalSystem.Customer.CustomerService;
import com.ExtVision.RentalSystem.DVD.DVDGame;
import com.ExtVision.RentalSystem.LoginFunc.LoginClass;

import jakarta.transaction.Transactional;

@Service
public class ClerkService {

    private final CustomerService customerService;

    @Autowired
    public ClerkService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setCanRent(CustomerClass customer) {
        //make states for customer class
        customerService.setCanRent(String.valueOf(customer.getCustomerID()));
    }

    public CustomerClass getCustomerDetails(int customerID) {
        return customerService.getCustomerDetails(String.valueOf(customerID));
    }

    public void updateCustomerProfile(CustomerClass customer, String name, String address, int phoneNum, String email) {
        customer.setName(name);
        customer.setAddress(address);
        customer.setphoneNum(phoneNum);
        customer.setEmail(email);
    }

    public void addDVDGame(DVDGame dvdGame) {
        //implement when DVDgame has a db to store DVDgames
    }

    public void removeDVDGame(DVDGame dvdGame) {
        //implement when DVDgame has a db to store DVDgames
    }

    public void addCustomer(int customerID, String name, String address, String phone, String email) {
        try {
            CustomerFactory.createCustomer(customerID, name, address, phone, email);
        } catch (Exception e) {
            System.out.println("ERROR: Couldn't create customer");
            e.printStackTrace();
        }
    }

    public void removeCustomer(int accountID) {
        try {
            LoginClass.removeAccount(accountID);
        } catch (Exception e) {
            System.out.println("ERROR: Couldn't remove account");
        }
    }

    // process a rental
    @Transactional
    public void processRental(CustomerClass cust, DVDGame disc) {
        try {
            if (disc.getState() == disc.getState()) {
                
            }
            else {
                System.out.println("You can't rent any DVDs!!!");
            }
        }
        catch (Exception e ) {
            System.out.println("ERROR: Couldn't rent DVDGame");
            e.printStackTrace();
        }
    }

    // process a return
    @Transactional
    public void processReturn(CustomerClass cust, DVDGame disc) {
        try {
            
        }
        catch (Exception e) {
            System.out.println("ERROR: Couldn't return DVDGame");
            e.printStackTrace();
        }
    }
}

