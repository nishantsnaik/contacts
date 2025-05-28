package com.nishant.contacts

import org.springframework.data.repository.CrudRepository

interface ContactRepository : CrudRepository<Contact, Long>
