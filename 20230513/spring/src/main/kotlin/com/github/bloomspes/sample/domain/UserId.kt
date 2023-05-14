package com.github.bloomspes.sample.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class UserId(
    @Column(name = "id")
    val value: String,
) {
    protected constructor() : this("")

    companion object {
        fun generate(): UserId {
            return UserId(newTsid())
        }

        private fun newTsid(): String {
            return EntityId.newTsid()
        }
    }
}
