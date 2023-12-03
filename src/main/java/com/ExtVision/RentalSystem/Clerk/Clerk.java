package com.ExtVision.RentalSystem.Clerk;

import com.ExtVision.RentalSystem.Observer.Observer;
import com.ExtVision.RentalSystem.DVD.State;
import com.ExtVision.RentalSystem.LoginFunc.LoginClass;

public class Clerk implements Observer {
    // declare attributes for the Clerk object
    protected int accountId;
    private String username;
    private String password;
    protected boolean active;
    protected boolean admin;

    // empty contructor that creates an account with default values
    public Clerk () {
        this.accountId = LoginClass.getAccountListLength();
        this.username = "NaN";
        this.password = "NaN";
        this.active = false;
        this.admin = false;
        addAccount(this);
    }

    // populated constuctor to add fields
    public Clerk (String username, String password, boolean active, boolean admin) {
        this.accountId = LoginClass.getAccountListLength();
        this.username = username;
        this.password = password;
        this.active = active;
        this.admin = admin;
        addAccount(this);
    }

    // class to change a clerk's password (by an admin)
    public void changePassword(Clerk clerk, String password) {
        if (this.admin == true) {
            clerk.password = password;
        }
    }

    // getter for accountId
    public int getAccountId() {
        return accountId;
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

    private void addAccount(Clerk account) {
        LoginClass.removeAccount(account.accountId);
    }

    private void removeAccount(int accountId) {
        LoginClass.removeAccount(accountId);
    }

    //Observer update method
    @Override
    public void update(Observer observer, int itemID, State state) {
        //implement at a later date
    }
}
