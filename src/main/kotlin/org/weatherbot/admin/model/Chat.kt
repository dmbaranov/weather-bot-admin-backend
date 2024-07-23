package org.weatherbot.admin.model

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