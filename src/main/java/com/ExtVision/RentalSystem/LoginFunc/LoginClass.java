package com.ExtVision.RentalSystem.LoginFunc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ExtVision.RentalSystem.LoginFunc.LoginStateFactory.LoginState;

import static com.ExtVision.RentalSystem.LoginFunc.LoginStateFactory.getLoginStateMessage;

@Service
public abstract class LoginClass {
    private Map<String, String> loginCredentials;
    private static List<Integer> accounts;
    private int accountId;
    protected boolean active;
    
    public LoginClass() {
        loginCredentials = new HashMap<>();
        this.active = false;
    }

    public LoginClass(String username, String password, boolean active) {
        loginCredentials = new HashMap<>();
        loginCredentials.put(username, encryptPassword(password));
        this.active = active;
    }

    public String registerAccount(String username, String password) {
        if (!loginCredentials.containsKey(username)) {
            loginCredentials.put(username, encryptPassword(password));
            accountId = accounts.size();
            accounts.add(accountId);
            // Additional logic to add customer to the database or customer management system
            return getLoginStateMessage(LoginState.LOGGED_IN);
        } else {
            return getLoginStateMessage(LoginState.LOGIN_FAILED);
        }
    }

    public String login(String username, String password) {
        if (loginCredentials.containsKey(username) && checkPassword(password, loginCredentials.get(username))) {
            return getLoginStateMessage(LoginState.LOGGED_IN);
        } else {
            return getLoginStateMessage(LoginState.LOGIN_FAILED);
        }
    }

    public String logout(String username) {
        // Logic to handle logout, like updating system logs or customer status
        return getLoginStateMessage(LoginState.LOGGED_OUT);
    }

    public String resetPassword(String username, String oldPassword, String newPassword) {
        if (loginCredentials.containsKey(username) && checkPassword(oldPassword, loginCredentials.get(username))) {
            loginCredentials.put(username, encryptPassword(newPassword));
            return getLoginStateMessage(LoginState.PASSWORD_RESET_SUCCESSFUL);
        } else {
            return getLoginStateMessage(LoginState.PASSWORD_RESET_FAILED);
        }
    }

    private String encryptPassword(String password) {
        // Password encryption logic should be implemented here
        return password; // This is a placeholder
    }

    private String decryptPassword(String password) {
        // Password decryption logic should be implemented here
        return password; // This is a placeholder
    }

    private boolean checkPassword(String inputPassword, String storedPassword) {
        // Password comparison logic should be implemented here
        return inputPassword.equals(decryptPassword(storedPassword)); // This is a placeholder
    }

    // add user to list of accounts
    public static void addAccount(LoginClass user) {
        accounts.add(user.accountId);
    }

    // remove user from list of accounts
    public static void removeAccount(int accountID) {
        accounts.remove(accounts.get(accountID));
    }

    // get size of arraylist to generate accountId for users
    public static int getAccountListLength() {
        return accounts.size();
    }

    public static LoginClass getAccount(int accountId) {
        return null;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}


