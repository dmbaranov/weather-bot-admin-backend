package org.weatherbot.admin.chat.db

import jakarta.persistence.*
import org.weatherbot.admin.chat.Platform

@Entity
@Table(name = "chat")
class Chat(
    @Id val id: String,
    val name: String,
    @Enumerated(EnumType.STRING) val platform: Platform,
    val swearwordsConfig: String
)
