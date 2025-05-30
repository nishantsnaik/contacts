package com.nishant.contacts

import org.springframework.stereotype.Service

@Service
class ContactService(val repository: ContactRepository,
                     private val kafkaProducer: KafkaProducerService,
    ) {
    fun create(request: ContactRequest): Contact {

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

        return repository.save(contact)
    }
    fun getAll(): List<Contact> = repository.findAll().toList()
}
