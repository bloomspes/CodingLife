package com.github.bloomspes.buckpal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.bloomspes.buckpal.models.Account;
import com.github.bloomspes.buckpal.models.AccountNumber;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(AccountNumber accountNumber);

    Account save(Account account);
}
