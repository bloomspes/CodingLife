package com.example.urlshortener.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenerController {

    // TODO: Make this method return a ShortenerService response!
    @PostMapping
    public String shortenUrl() {
        return "shortened";
    }
}
