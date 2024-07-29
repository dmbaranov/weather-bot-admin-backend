package org.weatherbot.admin.member

import org.springframework.stereotype.Service

@Service
class MemberService(private val memberRepository: MemberRepository) {
    fun getMembers(chatId: String): List<ChatMember> = memberRepository.findChatMembersByIdChatId(chatId)
}