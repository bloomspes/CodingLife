package com.github.bloomspes.sample.services

import java.time.LocalDateTime
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.github.bloomspes.sample.domain.User
import com.github.bloomspes.sample.domain.UserId
import com.github.bloomspes.sample.repositories.UserRepository

@Service
@Transactional
class CreateUserService(
    private val userRepository: UserRepository,
) {
    fun createUser(
        name: String,
        email: String,
    ) {
        val id = UserId.generate()
        val user = User(id, name, email)

        userRepository.save(user)
    }
}
