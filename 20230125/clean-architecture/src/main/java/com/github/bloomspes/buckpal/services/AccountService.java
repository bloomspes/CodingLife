package com.github.bloomspes.buckpal.services;

import jakarta.transaction.Transactional;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import com.github.bloomspes.buckpal.exceptions.AccountNotFound;
import com.github.bloomspes.buckpal.models.Account;
import com.github.bloomspes.buckpal.models.AccountNumber;
import com.github.bloomspes.buckpal.repositories.AccountRepository;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account number(AccountNumber accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFound(accountNumber));
    }
}
