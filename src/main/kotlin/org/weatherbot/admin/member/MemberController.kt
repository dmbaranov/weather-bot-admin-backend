package org.weatherbot.admin.member

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(private val memberService: MemberService) {
    @GetMapping("/v1/members")
    fun getMembers(@RequestParam chatId: String): List<ChatMember> = memberService.getMembers(chatId)

    @PutMapping("/v1/members/{chatId}/{userId}")
    fun updateMembers(
        @PathVariable chatId: String,
        @PathVariable userId: String,
        @RequestBody body: UpdateMemberDto
    ): ChatMember? =
        memberService.updateMember(chatId, userId, body)
}