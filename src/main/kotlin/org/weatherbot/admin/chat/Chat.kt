package org.weatherbot.admin.chat

import jakarta.persistence.*
import org.weatherbot.admin.common.Platform

@Entity
@Table(name = "chat")
class Chat(
    @Id val id: String,
    val name: String,
    @Enumerated(EnumType.STRING) val platform: Platform,
)
