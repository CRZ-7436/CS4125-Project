package com.ExtVision.RentalSystem.LoginFunc;

import java.util.HashMap;

import com.ExtVision.RentalSystem.Clerk.Clerk;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public abstract class Account {
    @Id
    @GeneratedValue

    public int accountId;
    @ElementCollection
    private HashMap<String, String> loginCredentials;
    public boolean admin;
    public boolean active;

    public Account() {
        accountId = LoginClass.getAccountListLength();
        loginCredentials  = new HashMap<>();
        loginCredentials.put("", "");
        admin = false;
        active = false;
    }

    public Account(String username, String password, boolean admin) {
        accountId = LoginClass.getAccountListLength();
        loginCredentials  = new HashMap<>();
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
