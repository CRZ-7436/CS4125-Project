package com.ExtVision.RentalSystem.Customertest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ExtVision.RentalSystem.Customer.CustomerClass;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerClassTest {

    private CustomerClass customer;

    @BeforeEach
    void init() {
        customer = new CustomerClass(1, "John Doe", "1234 Main St", 1234567890, "john@example.com");
    }

    @Test
    void testCustomerConstructorAndGetters() {
        assertEquals(1, customer.getaccountId());
        assertEquals("John Doe", customer.getName());
        assertEquals("1234 Main St", customer.getAddress());
        assertEquals(1234567890, customer.getPhoneNum());
        assertEquals("john@example.com", customer.getEmail());
        assertEquals("ACTIVE", customer.getAccountStatus());
    }

    @Test
    void testSetters() {
        customer.setaccountId(2);
        customer.setName("Jane Doe");
        customer.setAddress("5678 Elm St");
        customer.setphoneNum(9876543210L);
        customer.setEmail("jane@example.com");
        customer.setAccountStatus("INACTIVE");

        assertEquals(2, customer.getaccountId());
        assertEquals("Jane Doe", customer.getName());
        assertEquals("5678 Elm St", customer.getAddress());
        assertEquals(9876543210L, customer.getPhoneNum());
        assertEquals("jane@example.com", customer.getEmail());
        assertEquals("INACTIVE", customer.getAccountStatus());
    }

    @Test
    void isValidEmailValidTest() {
        assertTrue(customer.isValidEmail("validemail@example.com"));
    }

    @Test
    void isValidEmailInvalidTest() {
        assertFalse(customer.isValidEmail("invalidemail"));
    }

    @Test
    void updateProfileValidEmail() {
        customer.updateProfile("New Name", "New Address", 1122334455, "newemail@example.com");
        assertEquals("New Name", customer.getName());
        assertEquals("New Address", customer.getAddress());
        assertEquals(1122334455, customer.getPhoneNum());
        assertEquals("newemail@example.com", customer.getEmail());
    }

    @Test
    void updateProfileInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            customer.updateProfile("New Name", "New Address", 1122334455, "invalidemail");
        });
    }

   
}
