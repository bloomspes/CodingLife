package com.github.bloomspes.buckpal.services;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.bloomspes.buckpal.models.Account;
import com.github.bloomspes.buckpal.models.AccountNumber;
import com.github.bloomspes.buckpal.repositories.AccountRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AccountServiceTest {
    AccountService accountService;

    AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);

        given(accountRepository.findByAccountNumber(any()))
                .willReturn(Optional.of(Account.fake("1234-5678-9012")));

        accountService = new AccountService(accountRepository);
    }

    @Test
    void account() {
        AccountNumber accountNumber = new AccountNumber("1234-5678-9012");

        Account number = accountService.number(accountNumber);

        verify(accountRepository).findByAccountNumber(accountNumber);

        assertThat(number.accountNumber()).isEqualTo(accountNumber);
    }
}
