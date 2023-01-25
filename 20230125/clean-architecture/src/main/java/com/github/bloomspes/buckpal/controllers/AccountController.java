package com.github.bloomspes.buckpal.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.bloomspes.buckpal.dtos.AccountDto;
import com.github.bloomspes.buckpal.models.Account;
import com.github.bloomspes.buckpal.models.AccountNumber;
import com.github.bloomspes.buckpal.services.AccountService;

@RestController
@RequestMapping("accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("me")
    public AccountDto account(
            @RequestAttribute("accountNumber") AccountNumber accountNumber
            ) {
        Account account = accountService.number(accountNumber);

        return account.toDto();
    }
}
