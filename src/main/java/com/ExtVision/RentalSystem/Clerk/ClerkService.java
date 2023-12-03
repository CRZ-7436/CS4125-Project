package com.ExtVision.RentalSystem.Clerk;

import com.ExtVision.RentalSystem.Customer.CustomerClass;
import com.ExtVision.RentalSystem.DVD.DVDGame;

public interface ClerkService {
    void setCanRent(CustomerClass customer);s
    CustomerClass getCustomerDetails(int customerID);
    void updateCustomerProfile(CustomerClass customer, String name, String address, int phoneNum, String email);
    void addDVDGame(DVDGame dvdGame);
    void removeDVDGame(DVDGame dvdGame);
    void addCustomer(int customerID, String name, String address, String phone, String email);
    void removeCustomer(int accountID);
}
