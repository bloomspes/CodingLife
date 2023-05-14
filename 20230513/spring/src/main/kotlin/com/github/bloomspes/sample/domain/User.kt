package com.github.bloomspes.sample.domain

import java.time.LocalDateTime
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import com.github.bloomspes.sample.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @EmbeddedId
    private val id: UserId,

    @Column(name = "name")
    val name: String,

    @Column(name = "email")
    val email: String,
): BaseTimeEntity() {
    protected constructor() : this(UserId(""), "", "")
}
