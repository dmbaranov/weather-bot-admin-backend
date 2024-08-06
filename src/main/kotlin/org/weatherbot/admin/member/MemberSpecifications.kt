package org.weatherbot.admin.member

import org.springframework.data.jpa.domain.Specification

object MemberSpecifications {
    fun hasChatId(chatId: String?): Specification<ChatMember>? = chatId?.let {
        Specification { root, _, criteriaBuilder ->
            criteriaBuilder.equal(
                root.get(ChatMember_.id).get(ChatMemberId_.chatId), it
            )
        }

    }

    fun hasUserId(userId: String?): Specification<ChatMember>? = userId?.let {
        Specification { root, _, criteriaBuilder ->
            criteriaBuilder.equal(
                root.get(ChatMember_.botUser).get(BotUser_.id), it
            )
        }
    }
}