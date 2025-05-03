package org.weatherbot.admin.messaging

import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MessagingConfig {
    companion object {
        const val MESSAGING_EXCHANGE = "weatherbot.exchange"
    }


    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate =
        RabbitTemplate(connectionFactory).apply { this.messageConverter = Jackson2JsonMessageConverter() }

    @Bean
    fun appExchange() = TopicExchange(MESSAGING_EXCHANGE)
}
