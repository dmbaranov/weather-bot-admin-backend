package org.weatherbot.admin.chat_config

import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.weatherbot.admin.messaging.MessagingService
import org.weatherbot.admin.messaging.RoutingKey

enum class ChatConfigRoutingKey(override val pattern: String) : RoutingKey {
    UPDATED("chat-config.updated")
}

@Configuration
class ChatConfigMessagingConfig(private val exchange: TopicExchange, private val messagingService: MessagingService) {
    @Bean
    fun chatConfigMessaging(): Declarables {
        return messagingService.createCommonBindings(exchange, ChatConfigRoutingKey.entries)
    }
}
