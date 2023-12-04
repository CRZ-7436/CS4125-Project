package com.ExtVision.RentalSystem.LoginFunc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepo;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepo = accountRepository;
    }

    public List<Account> getAccounts() {
        return accountRepo.listAccounts();
    }

    public Account getAccountById(String accountId) {
        return accountRepo.getAccountById(accountId);
    }
}
