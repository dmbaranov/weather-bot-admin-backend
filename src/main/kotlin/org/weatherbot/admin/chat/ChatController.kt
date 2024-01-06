package org.weatherbot.admin.chat

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.weatherbot.admin.chat.db.Chat
import java.util.*

@RestController
class ChatController(val chatService: ChatService) {
    @GetMapping("/chats")
    fun getAllChats(): Iterable<Chat> = chatService.getAllChats()

    @GetMapping("/chats/{chatId}")
    fun getSingleChat(@PathVariable chatId: String): Optional<Chat> = chatService.getSingleChat(chatId)
}