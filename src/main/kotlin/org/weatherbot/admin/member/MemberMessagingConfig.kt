package org.weatherbot.admin.member

import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.weatherbot.admin.messaging.MessagingService


enum class MemberRoutingKey(val key: String) {
    UPDATED("member.updated"),
}


@Configuration
class MemberMessagingConfig(private val exchange: TopicExchange, private val messagingService: MessagingService) {
    @Bean
    fun memberMessaging(): Declarables {
        return messagingService.createCommonBindings(exchange, MemberRoutingKey.entries.map { it.key })
    }
}