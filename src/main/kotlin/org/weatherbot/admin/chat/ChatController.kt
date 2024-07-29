package org.weatherbot.admin.chat

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class ChatController(private val chatService: ChatService) {
    @GetMapping("/v1/chats")
    fun getAllChats(): Iterable<Chat> = chatService.getAllChats()

    @GetMapping("/v1/chats/{chatId}")
    fun getSingleChat(@PathVariable chatId: String): Optional<Chat> = chatService.getSingleChat(chatId)

    @GetMapping("/v1/chats/platform/{platform}")
    fun getPlatformChats(@PathVariable platform: Platform): Iterable<Chat> = chatService.getPlatformChats(platform)
}