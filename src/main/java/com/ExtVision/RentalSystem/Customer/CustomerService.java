package com.ExtVision.RentalSystem.Customer;

public interface CustomerService {
    boolean canRent(String customerID);
    void rentItem(String customerID, int itemID);
    void returnItem(String customerID, int itemID);
    void payFees(String customerID, float amount);
    CustomerClass getCustomerDetails(String customerID);
    void updateCustomerProfile(String customerID, String name, String address, double phoneNum, String email);
}
