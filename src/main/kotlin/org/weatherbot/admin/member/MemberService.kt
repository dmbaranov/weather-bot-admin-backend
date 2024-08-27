package org.weatherbot.admin.member

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class MemberService(private val memberRepository: MemberRepository) {
    fun getMembers(chatId: String, userId: String?): List<ChatMember> {
        val spec =
            Specification.where(MemberSpecifications.hasChatId(chatId)).and(MemberSpecifications.hasUserId(userId))
        val sort = Sort.by((Sort.Direction.ASC), ChatMemberId_.botUserId.name)

        return memberRepository.findAll(spec, sort)
    }

    fun updateMember(chatId: String, userId: String, updateData: UpdateMemberDto): ChatMember? {
        var member = memberRepository.findById(ChatMemberId(chatId, userId)).orElseThrow {
            MemberNotFound("User with id $userId from chat $chatId not found")
        }

        updateData.name?.let { member.botUser.name = it }
        updateData.banned?.let { member.banned = it }
        updateData.deleted?.let { member.deleted = it }
        updateData.moderator?.let { member.moderator = it }

        return memberRepository.save(member)
    }
}