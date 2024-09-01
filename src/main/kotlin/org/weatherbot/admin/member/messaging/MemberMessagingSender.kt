package org.weatherbot.admin.member.messaging

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class MemberMessagingSender(private val rabbitTemplate: RabbitTemplate) {
    private val mapper = jacksonObjectMapper().apply { registerModule(kotlinModule()) }

    fun send(routingKey: MemberRoutingKey, data: Any) {
        rabbitTemplate.convertAndSend(
            MemberMessagingConfig.MEMBER_EXCHANGE,
            routingKey.name,
            mapper.writeValueAsString(data),
        )
    }
}