package org.weatherbot.admin.chat

import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

enum class ChatRoutingKey(val key: String) {
    MESSAGE("chat.message"),
    UPDATED("chat.updated")
}

@Configuration
class ChatMessagingConfig(private val exchange: TopicExchange) {
    companion object {
        const val MESSAGE_QUEUE = "message"
        const val UPDATED = "chat-updated"
    }

    @Bean
    fun chatMessageQueue() = Queue(MESSAGE_QUEUE, false)

    @Bean
    fun chatUpdatedQueue() = Queue(UPDATED, false)

    @Bean
    fun chatMessagingBinding() = BindingBuilder.bind(chatMessageQueue()).to(exchange).with(ChatRoutingKey.MESSAGE.key)

    @Bean
    fun chatUpdatedBinding() = BindingBuilder.bind(chatUpdatedQueue()).to(exchange).with(ChatRoutingKey.UPDATED.key)
}