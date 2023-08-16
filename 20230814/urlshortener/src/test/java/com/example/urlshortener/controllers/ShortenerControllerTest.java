package com.example.urlshortener.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.urlshortener.applications.ShortenerService;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShortenerController.class)
class ShortenerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShortenerService shortenerService;

    @Test
    @DisplayName("POST / returns 200 OK")
    void shortenUrl() throws Exception {
        given(shortenerService.shorten("https://google.com"))
                .willReturn("hash");

        mockMvc.perform(
                post("/")
                        .content("{\"url\": \"https://google.com\"}")
                        .contentType("application/json"))
                .andExpect(status().isOk());

        verify(shortenerService).shorten("https://google.com");
    }

    @Test
    @DisplayName("GET /{hash} returns 301 Moved Permanently")
    void resolve() throws Exception {
        given(shortenerService.resolve("hash"))
                .willReturn("https://google.com");

        mockMvc.perform(get("/hash"))
                .andExpect(header().string("Location", "https://google.com"))
                .andExpect(header().string("Connection", "close"))
                .andExpect(status().isMovedPermanently());
    }
}
