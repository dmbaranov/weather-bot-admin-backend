package org.weatherbot.admin.chat_config

import com.fasterxml.jackson.annotation.JsonValue
import jakarta.persistence.*

enum class ChatSwearwords(@JsonValue val swearwords: String) {
    `fun`("fun"),
    angry("angry"),
    basic("basic")
}

@Entity
@Table(name = "chat_config")
class ChatConfig(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val chatId: String,
    var config: String
)
