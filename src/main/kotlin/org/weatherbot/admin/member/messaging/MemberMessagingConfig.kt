package org.weatherbot.admin.member.messaging

import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

enum class UserAction(private val action: String) {
    UPDATE("user_update"),
}

@Configuration
class MemberMessagingConfig {
    companion object {
        const val MEMBER_EXCHANGE = "weatherbot.exchange"
        const val MEMBER_QUEUE = "member_queue"
    }

    @Bean
    fun memberQueue() = Queue(MEMBER_QUEUE, false)

    @Bean
    fun memberExchange() = TopicExchange(MEMBER_EXCHANGE)

    @Bean
    fun memberUpdateBinding() = BindingBuilder.bind(memberQueue()).to(memberExchange()).with(MEMBER_QUEUE)
}