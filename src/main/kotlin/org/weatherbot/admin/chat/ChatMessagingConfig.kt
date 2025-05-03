package org.weatherbot.admin.chat

import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.weatherbot.admin.messaging.MessagingService
import org.weatherbot.admin.messaging.RoutingKey

enum class ChatRoutingKey(override val pattern: String) : RoutingKey {
    MESSAGE("chat.message"),
    SWEARWORDS_UPDATED("chat.swearwords-updated")

}

@Configuration
class ChatMessagingConfig(private val exchange: TopicExchange, private val messagingService: MessagingService) {

    @Bean
    fun chatMessaging(): Declarables {
        return messagingService.createCommonBindings(exchange, ChatRoutingKey.entries)
    }
}
