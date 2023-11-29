package com.ExtVision.RentalSystem.Clerk;

import com.ExtVision.RentalSystem.LoginFunc.LoginClass;

public abstract class Clerk extends LoginClass {
    private String username;
    private String password;
    private boolean active;
    private boolean admin;
}
