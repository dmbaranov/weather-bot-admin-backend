package org.weatherbot.admin.chat

import org.springframework.web.bind.annotation.*
import org.weatherbot.admin.common.Platform

@RestController
class ChatController(private val chatService: ChatService) {
    @GetMapping("/v1/chats")
    fun getAllChats(): Iterable<Chat> = chatService.getAllChats()

    @GetMapping("/v1/chats/{chatId}")
    fun getSingleChat(@PathVariable chatId: String): Chat = chatService.getSingleChat(chatId)

    @GetMapping("/v1/chats/swearwords")
    fun getChatSwearwords(): List<ChatSwearwords> = chatService.getChatSwearwords()

    // deprecated
    @GetMapping("/v1/chats/platform/{platform}")
    fun getPlatformChats(@PathVariable platform: Platform): Iterable<Chat> = chatService.getPlatformChats(platform)

    @PostMapping("/v1/chats/{platform}/{chatId}/message")
    fun sendMessage(@PathVariable platform: Platform, @PathVariable chatId: String, @RequestBody body: SendMessageDto) =
        chatService.sendMessage(platform, chatId, body)

    @PutMapping("/v1/chats/{platform}/{chatId}/swearwords")
    fun updateChatSwearwords(
        @PathVariable platform: Platform,
        @PathVariable chatId: String,
        @RequestBody body: UpdateChatSwearwordsDto
    ) = chatService.updateChatSwearwords(platform, chatId, body)
}