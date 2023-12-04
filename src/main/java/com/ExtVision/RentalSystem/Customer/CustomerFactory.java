package com.ExtVision.RentalSystem.Customer;

public class CustomerFactory {

    public static CustomerClass createCustomer(Integer customerID, String name, String address, double phone, String email) {
        
        return new CustomerClass(customerID, name, address,  phone,  email);
    }
}
