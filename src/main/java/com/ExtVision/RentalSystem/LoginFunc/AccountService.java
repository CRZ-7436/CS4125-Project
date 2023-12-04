package com.ExtVision.RentalSystem.LoginFunc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class AccountService {
    private final AccountRepository accountRepo;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        accountRepo = accountRepository;
    }

    public List<Account> getAccounts() {
        return accountRepo.getAccounts();
    }

    public Account getAccount(Integer accountId) {
        return accountRepo.getAccountById(accountId);
    }
}
