package com.github.bloomspes.sample.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import io.mockk.every
import io.mockk.verify
import com.ninjasquad.springmockk.MockkBean
import com.github.bloomspes.sample.services.CreateUserService

@WebMvcTest(UserController::class)
internal class UserControllerTest {
    @Autowired
    protected lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var userService: CreateUserService

    @Test
    @DisplayName("POST /users")
    fun create() {
        val json = """
            {
                "name": "tester",
                "email": "tester@example.com"
            }
        """.trimIndent()

        every { userService.createUser(any(), any()) } returns Unit

        mockMvc.perform(
            post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)

        verify { userService.createUser(eq("tester"), any()) }
    }
}
