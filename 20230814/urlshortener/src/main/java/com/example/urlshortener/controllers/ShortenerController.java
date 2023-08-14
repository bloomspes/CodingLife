package com.example.urlshortener.controllers;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.urlshortener.applications.ShortenerService;
import com.example.urlshortener.dtos.ShortenerRequest;
import com.example.urlshortener.dtos.ShortenerResponse;

@RestController
public class ShortenerController {
    private final ShortenerService shortenerService;

    public ShortenerController(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    @PostMapping
    public ShortenerResponse shortenUrl(@RequestBody ShortenerRequest request) {
        String hash = shortenerService.shorten(request.url());

        return new ShortenerResponse(hash);
    }

    @GetMapping("/{hash}")
    public ResponseEntity<HttpStatus> resolve(@PathVariable String hash) {
        String target = shortenerService.resolve(hash);

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .location(URI.create(target))
                .header(HttpHeaders.CONNECTION, "close")
                .build();
    }
}
