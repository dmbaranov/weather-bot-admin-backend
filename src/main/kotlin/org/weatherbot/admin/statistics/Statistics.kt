package org.weatherbot.admin.statistics

import jakarta.persistence.*
import org.weatherbot.admin.chat.Chat
import org.weatherbot.admin.member.BotUser

@Entity
@Table(name = "command_statistics")
class Statistics(
    @Id
    val id: Long,
    val command: String,
    val timestamp: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    val chat: Chat,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bot_user_id")
    val user: BotUser
)