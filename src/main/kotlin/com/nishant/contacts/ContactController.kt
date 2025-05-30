package com.nishant.contacts


import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/contacts")
class ContactController(
    private val repository: ContactRepository,
    private val contactService: ContactService
) {

    @PostMapping
    fun createContact(@RequestBody request: ContactRequest): ResponseEntity<Contact> {

        val contact =  contactService.create(request)
        return ResponseEntity.ok(contact)
    }

    @GetMapping("/{id}")
    fun getContact(@PathVariable id: Long): ResponseEntity<Contact> =
        repository.findById(id)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())
}

data class ContactRequest(
    val id: Long,
    val name: String,
    val email: String
)
