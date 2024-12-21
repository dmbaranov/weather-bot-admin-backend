package org.weatherbot.admin.member

import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.weatherbot.admin.common.Platform


enum class MemberRoutingKey(val key: String) {
    UPDATED("member.updated"),
}


@Configuration
class MemberMessagingConfig(private val exchange: TopicExchange) {
    @Bean
    fun memberMessaging(): Declarables {
        val platforms = Platform.entries
        val routingKeys = MemberRoutingKey.entries;

        // TODO: deduplicate
        val bindings = platforms.flatMap { platform ->
            routingKeys.flatMap { routingKey ->
                val queueName = "${platform.platform}.${routingKey.key.replace(".", "-")}"
                val routingKeyValue = "${platform.platform}.${routingKey.key}"

                val queue = Queue(queueName, false)
                val binding = BindingBuilder.bind(queue).to(exchange).with(routingKeyValue)

                listOf(queue, binding)
            }
        }

        return Declarables(bindings)
    }
}