package org.weatherbot.admin.chat.db

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import org.weatherbot.admin.chat.Platform

@Entity
class Chat(
    @Id val id: String,
    val name: String,
    @Enumerated(EnumType.STRING) val platform: Platform,
    val swearwordsConfig: String
)
