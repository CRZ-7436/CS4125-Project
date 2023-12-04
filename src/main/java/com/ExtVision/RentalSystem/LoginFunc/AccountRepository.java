package com.ExtVision.RentalSystem.LoginFunc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> getAccounts();
    Account getAccountById(Integer accountId);
}
