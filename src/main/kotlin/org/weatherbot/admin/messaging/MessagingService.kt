package org.weatherbot.admin.messaging

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
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

    fun createCommonBindings(exchange: TopicExchange, routingKeys: List<String>): Declarables {
        val platforms = Platform.entries

        val bindings = platforms.flatMap { platform ->
            routingKeys.flatMap { routingKey ->
                val queueName = "${platform.platform}.${routingKey.replace(".", "-")}"
                val routingKeyValue = "${platform.platform}.${routingKey}"

                val queue = Queue(queueName, false)
                val binding = BindingBuilder.bind(queue).to(exchange).with(routingKeyValue)

                listOf(queue, binding)
            }
        }

        return Declarables(bindings)
    }
}