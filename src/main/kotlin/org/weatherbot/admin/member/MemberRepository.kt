package org.weatherbot.admin.member

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : CrudRepository<ChatMember, ChatMemberId> {
    fun findChatMembersByIdChatId(chatId: String): List<ChatMember>
}