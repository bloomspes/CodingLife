package com.example.urlshortener.errors;

public class HashUnknownException extends RuntimeException {
    public HashUnknownException(String hash) {
        super("Hash " + hash + " is unknown");
    }
}
