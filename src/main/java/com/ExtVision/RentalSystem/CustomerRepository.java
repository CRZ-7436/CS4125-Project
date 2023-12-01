package com.ExtVision.RentalSystem;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ExtVision.RentalSystem.Customer.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // Custom query methods if needed
}
