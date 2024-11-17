package org.weatherbot.admin.member

import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


enum class MemberRoutingKey(val key: String) {
    UPDATED("member.updated"),
}


@Configuration
class MemberMessagingConfig(private val exchange: TopicExchange) {
    companion object {
        const val MEMBER_UPDATED_QUEUE = "member-updated"
    }

    @Bean
    fun memberUpdatedQueue() = Queue(MEMBER_UPDATED_QUEUE, false)

    @Bean
    fun memberMessagingBinding() =
        BindingBuilder.bind(memberUpdatedQueue()).to(exchange).with(MemberRoutingKey.UPDATED.key)
}