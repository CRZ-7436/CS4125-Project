package com.ExtVision.RentalSystem.LoginFunc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ExtVision.RentalSystem.CustomerRepository;
import com.ExtVision.RentalSystem.LoginFunc.LoginStateFactory.LoginState;

import static com.ExtVision.RentalSystem.LoginFunc.LoginStateFactory.getLoginStateMessage;

@Service
public class LoginClass implements LoginClassInterface{
    private static List<Integer> accounts = new ArrayList<>();;
    public Integer accountId;
    private Map<String, String> loginCredentials;
    public boolean admin;
    public boolean active;
    public String username;
    public String password;

    @Autowired
    private CustomerRepository customerRepository;
    
    public LoginClass() {
        this.loginCredentials = new HashMap<>();
    }

    
    public LoginClass(String username, String password, boolean active2) {
        this.loginCredentials = new HashMap<>();
        this.loginCredentials.put(username, encryptPassword(password)); // Assign username and encrypted password
        this.username = username; // Set username
        this.password = password; // Set password
        accountId = accounts.size();
        accounts.add(accountId);
    }

    @Override
    public String registerAccount(String username, String password, boolean admin) {
        if (!loginCredentials.containsKey(username)) {
            loginCredentials.put(username, encryptPassword(password));
            accountId = accounts.size();
            accounts.add(accountId);
            this.setAdmin(admin);
            return getLoginStateMessage(LoginState.LOGGED_IN);
        } else {
            return getLoginStateMessage(LoginState.LOGIN_FAILED);
        }
    }
    @Override
    public int generateCustomerID(){
        return accounts.size();
    }

    @Override
    public String login(String username, String password) {
        if (loginCredentials.containsKey(username) && checkPassword(password, loginCredentials.get(username))) {
            return getLoginStateMessage(LoginState.LOGGED_IN);
        } else {
            return getLoginStateMessage(LoginState.LOGIN_FAILED);
        }
    }

    @Override
    public String logout(String username) {
        // Logic to handle logout, like updating system logs or customer status
        return getLoginStateMessage(LoginState.LOGGED_OUT);
    }

    @Override
    public String resetPassword(String username, String oldPassword, String newPassword) {
        if (loginCredentials.containsKey(username) && checkPassword(oldPassword, loginCredentials.get(username))) {
            loginCredentials.put(username, encryptPassword(newPassword));
            return getLoginStateMessage(LoginState.PASSWORD_RESET_SUCCESSFUL);
        } else {
            return getLoginStateMessage(LoginState.PASSWORD_RESET_FAILED);
        }
    }
    // Never got a chance to implemented the encryption of passwords left as placeholders
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

    private void setAdmin(boolean admin) {
        this.admin = admin;
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

    public static LoginClassInterface getAccount(int accountId) {
        return null;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public void setUsername(String username){
        this.username = username;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public void setpassword(String password){
        this.password = password;
    }
}


