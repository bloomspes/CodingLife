package com.github.bloomspes.sample.controllers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import com.github.bloomspes.sample.controllers.dtos.CreateUserDto
import com.github.bloomspes.sample.services.CreateUserService

@RestController
@RequestMapping("/users")
class UserController(
    private val createUserService: CreateUserService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody userDto: CreateUserDto,
    ): String {
        val user = userDto.user()
        createUserService.createUser(user.name, user.email)
        return "Created!"
    }
}
