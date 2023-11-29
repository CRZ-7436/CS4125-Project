package com.ExtVision.RentalSystem.Clerk;

import com.ExtVision.RentalSystem.LoginFunc.LoginClass;

/* The Clerk class is for employees at the rental store to use to interact with the system.
 * The account id is used to identify the user (both customers and clerks)
 * The username and password are stored at strings
 * There are two bools for if the account is active or not, and if the account is an admin or not (creating/changing users)
*/
public abstract class Clerk extends LoginClass {
    private int accountId;
    private String username;
    private String password;
    private boolean active;
    private boolean admin;
}
