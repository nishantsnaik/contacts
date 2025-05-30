package com.nishant.contacts

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("contacts")
data class Contact(
    @Id val id: UUID? = null,
    val name: String,
    val email: String
)
