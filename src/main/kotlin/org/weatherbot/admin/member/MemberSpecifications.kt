package org.weatherbot.admin.member

import org.springframework.data.jpa.domain.Specification

object MemberSpecifications {
    fun hasChatId(chatId: String?): Specification<ChatMember>? {
        return chatId?.let {
            Specification { root, _, criteriaBuilder ->
                criteriaBuilder.equal(
                    root.get<String>("id").get<String>("chatId"), it
                )
            }
        }
    }
}