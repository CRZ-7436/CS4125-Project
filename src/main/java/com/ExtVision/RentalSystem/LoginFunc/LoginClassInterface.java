package com.ExtVision.RentalSystem.LoginFunc;

public interface LoginClassInterface {

    String registerAccount(String username, String password, boolean admin);

    int generateCustomerID();

    String login(String username, String password);

    String logout(String username);

    String resetPassword(String username, String oldPassword, String newPassword);

    void setActive(boolean active);

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setpassword(String password);

}