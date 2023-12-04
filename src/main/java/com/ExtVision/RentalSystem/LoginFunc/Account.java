package com.ExtVision.RentalSystem.LoginFunc;

import java.util.HashMap;

import com.ExtVision.RentalSystem.Clerk.Clerk;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String accountId;

    @ElementCollection
    private HashMap<String, String> loginCredentials;
    public boolean admin;
    public boolean active;

    public Account() {
        accountId = String.valueOf(LoginClass.getAccountListLength());
        loginCredentials  = new HashMap<>();
        loginCredentials.put("", "");
        admin = false;
        active = false;
    }

    public Account(String username, String password, boolean admin) {
        accountId = String.valueOf(LoginClass.getAccountListLength());
        loginCredentials  = new HashMap<>();
        loginCredentials.put(username, password);
        this.admin = admin;
        active = false;
    }

    public String getAccountId() {
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

    public static void addAccount(Clerk clerk) {
    }
}
