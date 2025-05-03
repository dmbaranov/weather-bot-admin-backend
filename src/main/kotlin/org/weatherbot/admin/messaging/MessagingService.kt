package org.weatherbot.admin.messaging

import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service
import org.weatherbot.admin.common.Platform

@Service
class MessagingService(private val rabbitTemplate: RabbitTemplate) {

    fun send(platform: Platform, routingKey: RoutingKey, data: Any) {
        val routingKeyWithPlatform = "${platform.platform}.${routingKey.pattern}"

        rabbitTemplate.convertAndSend(
            MessagingConfig.MESSAGING_EXCHANGE,
            routingKeyWithPlatform,
            data
        )
    }

    fun createCommonBindings(exchange: TopicExchange, routingKeys: List<RoutingKey>): Declarables {
        val platforms = Platform.entries

        val bindings = platforms.flatMap { platform ->
            routingKeys.flatMap { routingKey ->
                val queueName = "${platform.platform}.${routingKey.pattern}"
                val routingKeyValue = "${platform.platform}.${routingKey.pattern}"

                val queue = Queue(queueName, false)
                val binding = BindingBuilder.bind(queue).to(exchange).with(routingKeyValue)

                listOf(queue, binding)
            }
        }

        return Declarables(bindings)
    }
}
