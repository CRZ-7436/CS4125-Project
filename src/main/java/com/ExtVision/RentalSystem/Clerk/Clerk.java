package com.ExtVision.RentalSystem.Clerk;

import com.ExtVision.RentalSystem.Observer.Observer;
import com.ExtVision.RentalSystem.DVD.State;
import com.ExtVision.RentalSystem.LoginFunc.Account;
import com.ExtVision.RentalSystem.LoginFunc.LoginClass;

public class Clerk extends Account implements Observer {

    // empty contructor that creates an account with default values
    public Clerk () {
        super();
        this.accountId = String.valueOf(LoginClass.getAccountListLength());
        Account.addAccount(this);
    }

    // populated constuctor to add fields
    public Clerk (String username, String password, boolean active, boolean admin) {
        super(username, password, admin);
        this.accountId = String.valueOf(LoginClass.getAccountListLength());
        Account.addAccount(this);
    }

    // deletes clerk if the clerk invoking the method is an admin
    public void deleteClerk(int accountId) {
        if (this.admin == true) {
            LoginClass.removeAccount(accountId);
        }
    }

    //Observer update method
    @Override
    public void update(int itemID, State state) {
        //implement at a later date
    }
}
