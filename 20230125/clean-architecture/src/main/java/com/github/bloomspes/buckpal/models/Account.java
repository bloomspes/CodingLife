package com.github.bloomspes.buckpal.models;

import java.time.LocalDateTime;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.github.bloomspes.buckpal.dtos.AccountDto;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private AccountNumber accountNumber;

    private String name;

    private Long amount;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Account(AccountNumber accountNumber, String name) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.amount = 0L;
    }

    public Account(Long id, AccountNumber accountNumber,
                   String name, Long amount) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.name = name;
        this.amount = amount;
    }

    public void transferTo(Account other, Long amount) {
        if (amount <= 0 || amount > this.amount) {
            throw new RuntimeException("The amount is not correct.");
        }

        this.amount -= amount;

        other.amount += amount;
    }

    public AccountNumber accountNumber() {
        return accountNumber;
    }

    public String name() {
        return name;
    }

    public Long amount() {
        return amount;
    }

    public AccountDto toDto() {
        return new AccountDto(accountNumber.value(), name, amount);
    }

    public static Account fake(String accountNumber) {
        return new Account(
                1L, new AccountNumber(accountNumber), "Buckpal", 50000L);
    }

    public static Account fake(AccountNumber accountNumber) {
        return Account.fake(accountNumber.value());
    }
}
