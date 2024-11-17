package org.weatherbot.admin.chat

import org.springframework.web.bind.annotation.*

@RestController
class ChatController(private val chatService: ChatService) {
    @GetMapping("/v1/chats")
    fun getAllChats(): Iterable<Chat> = chatService.getAllChats()

    @GetMapping("/v1/chats/{chatId}")
    fun getSingleChat(@PathVariable chatId: String): Chat = chatService.getSingleChat(chatId)

    // deprecated
    @GetMapping("/v1/chats/platform/{platform}")
    fun getPlatformChats(@PathVariable platform: Platform): Iterable<Chat> = chatService.getPlatformChats(platform)

    @PostMapping("/v1/chats/{chatId}/message")
    fun sendMessage(@PathVariable chatId: String, @RequestBody body: SendMessageDto) =
        chatService.sendMessage(chatId, body)
}