package com.github.bloomspes.sample.controllers.dtos

import com.github.bloomspes.sample.domain.User
import com.github.bloomspes.sample.domain.UserId

data class CreateUserDto(
    val name: String,
    val email: String,
) {
    fun user(): User {
        return User(
            UserId.generate(),
            name,
            email
        )
    }
}
