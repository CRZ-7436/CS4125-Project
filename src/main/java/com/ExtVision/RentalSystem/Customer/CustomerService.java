package com.ExtVision.RentalSystem.Customer;

public interface CustomerService {
    boolean canRent(int customerID);
    void setCanRent(int customerID);
    void rentItem(int customerID, int itemID);
    void returnItem(int customerID, int itemID);
    void payFees(int customerID, float amount);
    CustomerClass getCustomerDetails(int accountId);
    void updateCustomerProfile(int accountId, String name, String address, double phoneNum, String email);
}
