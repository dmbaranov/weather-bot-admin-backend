package org.weatherbot.admin.messaging

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service
import org.weatherbot.admin.common.Platform

@Service
class MessagingService(private val rabbitTemplate: RabbitTemplate) {
    private val mapper = jacksonObjectMapper().apply { registerModule(kotlinModule()) }

    fun send(platform: Platform, routingKey: String, data: Any) {
        val routingKeyWithPlatform = "$platform.$routingKey"

        rabbitTemplate.convertAndSend(
            MessagingConfig.MESSAGING_EXCHANGE,
            routingKeyWithPlatform,
            mapper.writeValueAsString(data)
        )
    }
}