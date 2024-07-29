package org.weatherbot.admin.member

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(val memberService: MemberService) {
    @GetMapping("/v1/members")
    fun getMembers(@RequestParam chatId: String): List<ChatMember> = memberService.getMembers(chatId)
}