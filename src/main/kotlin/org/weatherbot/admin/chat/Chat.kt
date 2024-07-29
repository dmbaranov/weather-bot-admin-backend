package org.weatherbot.admin.chat

import jakarta.persistence.*

@Entity
@Table(name = "chat")
class Chat(
    @Id val id: String,
    val name: String,
    @Enumerated(EnumType.STRING) val platform: Platform,
    val swearwordsConfig: String
)