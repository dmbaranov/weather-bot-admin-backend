package org.weatherbot.admin.model

import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.io.Serializable


@Embeddable
data class ChatMemberId(val botUserId: String, val chatId: String) : Serializable

@Entity
@Table(name = "chat_member")
class ChatMember(
    @EmbeddedId
    val userData: ChatMemberId,
    val deleted: Boolean,
    val banned: Boolean,
    val moderator: Boolean
)
