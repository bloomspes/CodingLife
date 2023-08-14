package com.example.urlshortener.applications;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.urlshortener.errors.HashUnknownException;

@Service
public class ShortenerService {
    private final RedisTemplate<String, String> redisTemplate;
    private final MessageDigest digest;

    public ShortenerService(
            RedisTemplate<String, String> redisTemplate, MessageDigest digest
    ) {
        this.redisTemplate = redisTemplate;
        this.digest = digest;
    }

    public String shorten(String url) {
        String hash = hash(url);
        redisTemplate.opsForValue().set(hash, url);

        return hash;
    }

    private String hash(String url) {
        byte[] bytes = digest.digest(url.getBytes());
        String hash = String.format("%32x", new BigInteger(1, bytes));

        return hash.substring(0, 6);
    }

    public String resolve(String hash) {
        String value = redisTemplate.opsForValue().get(hash);

        if (value == null) {
            throw new HashUnknownException(hash);
        }

        return value;
    }
}
