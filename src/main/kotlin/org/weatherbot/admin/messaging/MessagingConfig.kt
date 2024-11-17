package org.weatherbot.admin.messaging

import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MessagingConfig {
    companion object {
        const val MESSAGING_EXCHANGE = "weatherbot.exchange"
    }

    @Bean
    fun appExchange() = TopicExchange(MESSAGING_EXCHANGE)
}