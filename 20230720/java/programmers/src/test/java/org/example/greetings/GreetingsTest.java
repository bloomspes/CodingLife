package org.example.greetings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GreetingsTest {
    @Test
    void greetings() {
        assertThat(hello()).isEqualTo("hello world!");
    }

    private String hello() {
        return "hello world!";
    }
}
