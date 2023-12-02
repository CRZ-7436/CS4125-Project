package com.ExtVision.RentalSystem.Clerk;

import com.ExtVision.RentalSystem.Observer.Observer;
import com.ExtVision.RentalSystem.DVD.State;

public class ClerkClass extends Clerk implements Observer {
    // declare attributes for the Clerk object
    protected int accountId;
    private String username;
    private String password;
    protected boolean active;
    protected boolean admin;

    // empty contructor that creates an account with default values
    public ClerkClass () {
        this.accountId = getAccountListLength();
        this.username = "NaN";
        this.password = "NaN";
        this.active = false;
        this.admin = false;
        addAccount(this);
    }

    // populated constuctor to add fields
    public ClerkClass (String username, String password, boolean active, boolean admin) {
        this.accountId = getAccountListLength();
        this.username = username;
        this.password = password;
        this.active = active;
        this.admin = admin;
        addAccount(this);
    }

    // class to change a clerk's password (by an admin)
    public void changePassword(ClerkClass clerk, String password) {
        if (this.admin == true) {
            clerk.password = password;
        }
    }

    // get clerk based on the index of the accounts ArrayList
    public ClerkClass getClerk(int accountId) {
        //todo fix login class
        return null;
    }

    // check if the user specified is active
    public boolean getActive() {
        return this.active;
    }

    // set the clerk to active/inactive
    public void setActive(boolean active) {
        this.active = active;
    }

    // deletes clerk if the clerk invoking the method is an admin
    public void deleteClerk(int accountId) {
        if (this.admin == true) {
            removeAccount(accountId);
        }
    }

    //Observer update method
    @Override
    public void update(Observer observer, int itemID, State state) {
        //implement at a later date
    }
}