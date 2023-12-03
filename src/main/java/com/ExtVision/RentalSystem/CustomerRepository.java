package com.ExtVision.RentalSystem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ExtVision.RentalSystem.Customer.CustomerClass;
import com.ExtVision.RentalSystem.DVD.DVDGame;
@Repository
public interface CustomerRepository extends JpaRepository<CustomerClass, Integer> {
     List<CustomerClass> findByAccountStatus(String accountStatus);
}
