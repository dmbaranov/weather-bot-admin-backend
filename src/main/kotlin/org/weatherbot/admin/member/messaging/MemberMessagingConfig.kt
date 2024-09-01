package org.weatherbot.admin.member.messaging

import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

enum class MemberRoutingKey(private val key: String) {
    UPDATED("member.updated"),
}

@Configuration
class MemberMessagingConfig {
    companion object {
        const val MEMBER_EXCHANGE = "weatherbot.exchange"
        const val MEMBER_UPDATED_QUEUE = "member-updated"
    }

    @Bean
    fun memberUpdatedQueue() = Queue(MEMBER_UPDATED_QUEUE, false)

    @Bean
    fun memberExchange() = TopicExchange(MEMBER_EXCHANGE)

    @Bean
    fun memberUpdateBinding() =
        BindingBuilder.bind(memberUpdatedQueue()).to(memberExchange()).with(MemberRoutingKey.UPDATED.name)
}