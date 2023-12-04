package com.ExtVision.RentalSystem.LoginTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import com.ExtVision.RentalSystem.CustomerRepository;
import com.ExtVision.RentalSystem.LoginFunc.LoginClass;
import com.ExtVision.RentalSystem.LoginFunc.LoginStateFactory;
import com.ExtVision.RentalSystem.LoginFunc.LoginStateFactory.LoginState;

@ExtendWith(MockitoExtension.class)
public class LoginClassTest {

    @InjectMocks
    private LoginClass loginClass;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        // Setup common objects or configurations for all tests
    }

    @Test
    public void testRegisterAccount_NewUser() {
        String result = loginClass.registerAccount("newuser", "password", false);
        assertEquals(LoginStateFactory.getLoginStateMessage(LoginState.LOGGED_IN), result);
    }

    @Test
    public void testRegisterAccount_ExistingUser() {
        // Pre-register a user
        loginClass.registerAccount("existinguser", "password", false);

        // Attempt to register the same user again
        String result = loginClass.registerAccount("existinguser", "password", false);
        assertEquals(LoginStateFactory.getLoginStateMessage(LoginState.LOGIN_FAILED), result);
    }

    @Test
    public void testLogin_Successful() {
        // Pre-register a user
        loginClass.registerAccount("user", "password", false);

        // Test login
        String result = loginClass.login("user", "password");
        assertEquals(LoginStateFactory.getLoginStateMessage(LoginState.LOGGED_IN), result);
    }

    @Test
    public void testLogin_Failed() {
        String result = loginClass.login("nonexistent", "password");
        assertEquals(LoginStateFactory.getLoginStateMessage(LoginState.LOGIN_FAILED), result);
    }

    @Test
    public void testLogout() {
        // Mock logout behavior if necessary
        String result = loginClass.logout("user");
        assertEquals(LoginStateFactory.getLoginStateMessage(LoginState.LOGGED_OUT), result);
    }

    @Test
    public void testResetPassword_Successful() {
        // Pre-register a user
        loginClass.registerAccount("user", "oldPassword", false);

        // Test password reset
        String result = loginClass.resetPassword("user", "oldPassword", "newPassword");
        assertEquals(LoginStateFactory.getLoginStateMessage(LoginState.PASSWORD_RESET_SUCCESSFUL), result);
    }

    @Test
    public void testResetPassword_Failed() {
        
        loginClass.registerAccount("user", "oldPassword", false);

        
        String result = loginClass.resetPassword("user", "wrongPassword", "newPassword");
        assertEquals(LoginStateFactory.getLoginStateMessage(LoginState.PASSWORD_RESET_FAILED), result);
    }

    
}
