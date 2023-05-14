package com.github.bloomspes.sample.repositories

import org.springframework.data.jpa.repository.JpaRepository
import com.github.bloomspes.sample.domain.User
import com.github.bloomspes.sample.domain.UserId

interface UserRepository : JpaRepository<User, UserId> {
    fun existsByEmail(email: String): Boolean
}
