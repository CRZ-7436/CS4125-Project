package com.ExtVision.RentalSystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ExtVision.RentalSystem.Customer.CustomerClass;
@Repository
public interface CustomerRepository extends JpaRepository<CustomerClass, Integer> {
    // Custom query methods if needed
}
