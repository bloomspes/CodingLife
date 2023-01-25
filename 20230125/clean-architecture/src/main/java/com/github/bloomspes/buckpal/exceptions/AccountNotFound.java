package com.github.bloomspes.buckpal.exceptions;

import com.github.bloomspes.buckpal.models.AccountNumber;

public class AccountNotFound extends RuntimeException {

    public AccountNotFound(AccountNumber accountNumber) {
        super("Account is not found. (" + accountNumber.value() + ")");
    }
}
