package com.nishant.contacts

import com.nishant.contacts.avro.Contact
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducerService(
    private val kafkaTemplate: KafkaTemplate<String, Contact>
) {
    fun sendContact(contact: Contact) {
        val record = ProducerRecord("contacts-topic", contact.id.toString(), contact)
        kafkaTemplate.send(record)
    }
}
