package org.weatherbot.admin.messaging

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class MessagingService(private val rabbitTemplate: RabbitTemplate) {
    private val mapper = jacksonObjectMapper().apply { registerModule(kotlinModule()) }

    fun send(routingKey: String, data: Any) {
        rabbitTemplate.convertAndSend(
            MessagingConfig.MESSAGING_EXCHANGE,
            routingKey,
            mapper.writeValueAsString(data)
        )
    }
}