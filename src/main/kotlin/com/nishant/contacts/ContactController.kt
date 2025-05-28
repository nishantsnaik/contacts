package com.nishant.contacts


import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/contacts")
class ContactController(
    private val repository: ContactRepository,
    private val kafkaProducer: KafkaProducerService
) {

    @PostMapping
    fun createContact(@RequestBody request: ContactRequest): ResponseEntity<Contact> {

        val entity = Contact(
            name = request.name,
            email = request.email
        )
        val contact = repository.save(entity)

        // Build Avro Contact and send to Kafka
        val avroContact =
            com.nishant.contacts.avro.Contact.newBuilder()
                .setId(contact.id!!)
                .setName(contact.name)
                .setEmail(contact.email)
                .build()

        kafkaProducer.sendContact(avroContact)
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
