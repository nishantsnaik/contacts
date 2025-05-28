package com.nishant.contacts

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("contacts")
data class Contact(
    @Id val id: Long? = null,
    val name: String,
    val email: String
)
