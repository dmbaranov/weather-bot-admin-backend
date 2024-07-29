package org.weatherbot.admin.member

import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class MemberService(private val memberRepository: MemberRepository) {
    fun getMembers(chatId: String): List<ChatMember> {
        val spec = Specification.where(MemberSpecifications.hasChatId(chatId))

        return memberRepository.findAll(spec)
    }
}