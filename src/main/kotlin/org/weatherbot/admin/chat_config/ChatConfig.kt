package org.weatherbot.admin.chat_config

import jakarta.persistence.*

@Entity
@Table(name = "chat_config")
class ChatConfig(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val chatId: String,
    var config: String
)
