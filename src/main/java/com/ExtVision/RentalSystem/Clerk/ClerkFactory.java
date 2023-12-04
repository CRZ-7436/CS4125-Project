package com.ExtVision.RentalSystem.Clerk;

import org.springframework.stereotype.Component;

@Component
public class ClerkFactory {
    public static Clerk createClerk(String username, String password, boolean active, boolean admin) {
        return new Clerk(username, password, admin, admin);
    }
}
