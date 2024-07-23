package org.weatherbot.admin.chat

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.weatherbot.admin.model.ChatMember
import org.weatherbot.admin.model.ChatMemberId

@Repository
interface ChatMemberRepository : CrudRepository<ChatMember, ChatMemberId> {
}