package com.ExtVision.RentalSystem.Customer;

import java.util.List;

public interface CustomerClassInterface {

    // getters and setters
    Integer getaccountId();

    String getName();

    String getAddress();

    double getPhoneNum();

    String getEmail();

    String getAccountStatus();

    public String getUsername();

    List<Integer> getCurrentRentals();

    float getBalance();

    // Updates the account status
    void setAccountStatus(String accountStatus);
    void setUsername(String username);
    // Updates the balance
    void setBalance(float balance);

    void setaccountId(Integer accountId);

    void setName(String name);

    void setAddress(String address);

    void setphoneNum(double phoneNum);

    void setEmail(String email);

    boolean isValidEmail(String email);
    
    // Updates the customer's profile information
    void updateProfile(String name, String address, double phone, String email);

}