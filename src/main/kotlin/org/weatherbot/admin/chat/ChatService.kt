package org.weatherbot.admin.chat

import org.springframework.stereotype.Service
import org.weatherbot.admin.common.Platform
import org.weatherbot.admin.messaging.MessagingService

@Service
class ChatService(private val chatRepository: ChatRepository, private val messagingService: MessagingService) {
    fun getAllChats(): Iterable<Chat> = chatRepository.findAll()

    fun getSingleChat(chatId: String): Chat =
        chatRepository.findById(chatId).orElseThrow { ChatNotFound("Chat with id $chatId not found") }

    fun getPlatformChats(platform: Platform): Iterable<Chat> = chatRepository.findAllByPlatform(platform)

    fun sendMessage(platform: Platform, chatId: String, message: SendMessageDto) =
        messagingService.send(platform, ChatRoutingKey.MESSAGE, message)
}
