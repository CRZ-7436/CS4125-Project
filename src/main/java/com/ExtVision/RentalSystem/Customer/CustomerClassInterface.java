package com.ExtVision.RentalSystem.Customer;

import java.util.List;

public interface CustomerClassInterface {

    // getters and setters
    int getCustomerID();

    String getName();

    String getAddress();

    double getPhone();

    String getEmail();

    String getAccountStatus();

    List<Integer> getCurrentRentals();

    float getBalance();

    // Updates the account status
    void setAccountStatus(String accountStatus);

    // Updates the balance
    void setBalance(float balance);

    void setName(String name);

    void setAddress(String address);

    void setphoneNum(double phoneNum);

    void setEmail(String email);
    //validaiton check
    // More validation checks need to be added currently this is the only one just so we can easily create customer classes
    // They will be added as needed
    // Validates the email format using a regular format
    boolean isValidEmail(String email);
    //
    // Updates the customer's profile information
    // This method might need to be inside the clerk class but I added here so Customer objects aren't set 
    void updateProfile(String name, String address, double phone, String email);

}