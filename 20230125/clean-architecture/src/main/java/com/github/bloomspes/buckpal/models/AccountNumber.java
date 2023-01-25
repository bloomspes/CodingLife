package com.github.bloomspes.buckpal.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class AccountNumber {
    private String value;

    public AccountNumber() {

    }

    public AccountNumber(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "AccountNumber: " + value;
    }

    @Override
    public boolean equals(Object other) {
        return other != null &&
                other.getClass() == AccountNumber.class &&
                this.value.equals(((AccountNumber) other).value);
    }
}
