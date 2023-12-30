package org.weatherbot.admin.chat

import com.fasterxml.jackson.annotation.JsonValue

enum class Platform(@JsonValue val platform: String) {
    telegram("telegram"),
    discord("discord")
}