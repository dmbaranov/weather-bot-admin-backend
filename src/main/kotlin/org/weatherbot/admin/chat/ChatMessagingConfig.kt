package org.weatherbot.admin.chat

import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.weatherbot.admin.common.Platform

enum class ChatRoutingKey(val key: String) {
    MESSAGE("chat.message"),
    UPDATED("chat.updated")
}

@Configuration
class ChatMessagingConfig(private val exchange: TopicExchange) {

    @Bean
    fun chatMessaging(): Declarables {
        val platforms = Platform.entries
        val routingKeys = ChatRoutingKey.entries;

        // TODO: deduplicate
        val bindings = platforms.flatMap { platform ->
            routingKeys.flatMap { routingKey ->
                val queueName = "${platform.platform}.${routingKey.key.replace(".", "-")}"
                val routingKeyValue = "${platform.platform}.${routingKey.key}"

                val queue = Queue(queueName, false)
                val binding = BindingBuilder.bind(queue).to(exchange).with(routingKeyValue)

                listOf(queue, binding)
            }
        }

        return Declarables(bindings)
    }
}