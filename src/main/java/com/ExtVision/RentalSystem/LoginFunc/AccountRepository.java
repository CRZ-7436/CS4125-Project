package com.ExtVision.RentalSystem.LoginFunc;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    ArrayList<Account> getAccounts();
    ArrayList<Account> getCustomerAccounts();
    ArrayList<Account> getClerkAccounts();
    Account getAccountById(int accountId);
}
