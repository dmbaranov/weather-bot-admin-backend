package org.weatherbot.admin.member

import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table
import java.io.Serializable


@Embeddable
data class ChatMemberId(val chatId: String, val botUserId: String) : Serializable

@Entity
@Table(name = "bot_user")
class BotUser(
    @Id val id: String,
    var name: String,
    val isPremium: Boolean,
)

@Entity
@Table(name = "chat_member")
class ChatMember(
    @EmbeddedId
    val id: ChatMemberId,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("botUserId")
    @JoinColumn(name = "bot_user_id")
    val botUser: BotUser,

    var deleted: Boolean,
    var banned: Boolean,
    var moderator: Boolean
)
