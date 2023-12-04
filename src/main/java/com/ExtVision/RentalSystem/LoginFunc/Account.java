package com.ExtVision.RentalSystem.LoginFunc;

import java.util.HashMap;

public abstract class Account {
    public int accountId;
    private HashMap<String, String> loginCredentials  = new HashMap<>();
    public boolean admin;
    public boolean active;

    public Account() {
        accountId = LoginClass.getAccountListLength();
        loginCredentials.put("", "");
        admin = false;
        active = false;
    }

    public Account(String username, String password, boolean admin) {
        accountId = LoginClass.getAccountListLength();
        loginCredentials.put(username, password);
        this.admin = admin;
        active = false;
    }

    public int getAccountId() {
        return accountId;
    }

    public boolean getAdmin() {
        return admin;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
