package com.github.bloomspes.sample.domain

import java.io.Serializable
import io.hypersistence.tsid.TSID
import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
data class EntityId(
    @Column(name = "id")
    val value: String? = null
): Serializable {
    companion object {
        fun newTsid(): String {
            return TSID.Factory.getTsid().toString()
        }
    }
}
