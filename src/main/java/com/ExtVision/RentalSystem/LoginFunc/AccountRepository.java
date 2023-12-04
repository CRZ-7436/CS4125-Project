package com.ExtVision.RentalSystem.LoginFunc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> listAccounts();

    Account getAccountById(String accountId);
}
