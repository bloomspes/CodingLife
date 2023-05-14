package com.github.bloomspes.sample.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import io.mockk.every
import com.ninjasquad.springmockk.MockkBean
import com.github.bloomspes.sample.services.CheckEmailService

@WebMvcTest(CheckEmailController::class)
internal class CheckEmailControllerTest {
    @Autowired
    protected lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var checkEmailService: CheckEmailService

    @Test
    @DisplayName("GET /users?email={email}")
    fun checkEmail() {
        every { checkEmailService.checkEmailExisting(any()) } returns false

        mockMvc.perform(
            MockMvcRequestBuilders.get("/users")
                .param("email", "tester@example.com")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Email-Duplicate", "false")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @DisplayName("GET /users/http-servlet-response?email={email}")
    fun checkEmailUsingHttpServletResponse() {
        every { checkEmailService.checkEmailExisting(any()) } returns true

        mockMvc.perform(
            MockMvcRequestBuilders.get("/users/http-servlet-response")
                .param("email", "tester@example.com")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Email-Duplicate", "true")
        )
            .andExpect(MockMvcResultMatchers.status().isConflict)
    }

    @Test
    @DisplayName("GET /users/response-entity?email={email}")
    fun checkEmailUsingResponseEntity() {
        every { checkEmailService.checkEmailExisting(any()) } returns true

        val responseHeaders = HttpHeaders()
        responseHeaders.set("X-Email-Duplicate", "true")

        mockMvc.perform(
            MockMvcRequestBuilders.get("/users/response-entity")
                .param("email", "tester@example.com")
                .accept(MediaType.APPLICATION_JSON)
                .headers(responseHeaders)
        )
            .andExpect(MockMvcResultMatchers.status().isConflict)
    }
}
