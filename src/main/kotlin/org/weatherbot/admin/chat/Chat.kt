package org.weatherbot.admin.chat

import com.fasterxml.jackson.annotation.JsonValue
import jakarta.persistence.*
import org.weatherbot.admin.common.Platform

enum class ChatSwearwords(@JsonValue val swearwords: String) {
    `fun`("fun"),
    angry("angry"),
    basic("basic")
}

@Entity
@Table(name = "chat")
class Chat(
    @Id val id: String,
    val name: String,
    @Enumerated(EnumType.STRING) val platform: Platform,
    @Enumerated(EnumType.STRING) val swearwordsConfig: ChatSwearwords
)