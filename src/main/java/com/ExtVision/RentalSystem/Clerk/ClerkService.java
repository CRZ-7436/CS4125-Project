package com.ExtVision.RentalSystem.Clerk;

import com.ExtVision.RentalSystem.Customer.CustomerClass;

public interface ClerkService {
    void setCanRent(CustomerClass customer);
    CustomerClass getCustomerDetails(int customerID);
    void updateCustomerProfile(CustomerClass customer, String name, String address, int phoneNum, String email);
    void addDVDGame(DVDGame dvdGame);
    void removeDVDGame(DVDGame dvdGame);
}
