package com.ExtVision.RentalSystem.Customer;

public class CustomerFactory {

    public static CustomerClass createCustomer(int customerID, String name, String address, String phone, String email) {
        
        return new CustomerClass(customerID, name, address,  phone,  email);
    }
}
