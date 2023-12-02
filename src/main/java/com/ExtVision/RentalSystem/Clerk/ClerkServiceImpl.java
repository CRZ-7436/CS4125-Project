package com.ExtVision.RentalSystem.Clerk;

import com.ExtVision.RentalSystem.Customer.CustomerClass;
import com.ExtVision.RentalSystem.DVD.AvailableState;
import com.ExtVision.RentalSystem.DVD.DVDGame;

public class ClerkServiceImpl implements ClerkService {
    @Override
    public void setCanRent(CustomerClass customer) {
        //make states for customer class
        customer.setAccountStatus(null);
    }

    @Override
    public CustomerClass getCustomerDetails(int customerID) {
        //implement when the customer class has a method to search for a use by the accountID
        return null;
    }

    @Override
    public void updateCustomerProfile(CustomerClass customer, String name, String address, int phoneNum, String email) {
        customer.setName(name);
        customer.setAddress(address);
        customer.setphoneNum(phoneNum);
        customer.setEmail(email);
    }

    @Override
    public void addDVDGame(DVDGame dvdGame) {
        //implement when DVDgame has a db to store DVDgames
    }

    @Override
    public void removeDVDGame(DVDGame dvdGame) {
        //implement when DVDgame has a db to store DVDgames
    }

    @Override
    public void addCustomer(int customerID, String name, String address, String phone, String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCustomer'");
    }

    @Override
    public void removeCustomer(int accountID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeCustomer'");
    }

    // process a rental
    public void processRental(CustomerClass cust, DVDGame disc) {
        // TODO: DVDGame isn't implemented yet
        try {
            if (disc.getState() == new AvailableState()) {
                
            }
            else {
                System.out.println("You can't rent any DVDs!!!");
                //TODO: change this once UI is implemented
            }
        }
        catch (Exception e ) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }

    // process a return
    public void processReturn(CustomerClass cust, DVDGame disc) {
        // TODO: DVDGame isn't implemented yet
        try {
            
        }
        catch (Exception e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }
}
