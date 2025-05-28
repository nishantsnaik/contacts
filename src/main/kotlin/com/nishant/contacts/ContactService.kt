package com.nishant.contacts

import org.springframework.stereotype.Service

@Service
class ContactService(val repository: ContactRepository) {
    fun create(contact: Contact): Contact = repository.save(contact)
    fun getAll(): List<Contact> = repository.findAll().toList()
}
