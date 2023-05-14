package com.github.bloomspes.sample.services

import org.springframework.stereotype.Service
import com.github.bloomspes.sample.repositories.UserRepository

@Service
class CheckEmailService(
    private val userRepository: UserRepository,
) {
    fun checkEmailExisting(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }
}
