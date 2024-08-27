package org.weatherbot.admin.chat

import org.springframework.stereotype.Service

@Service
class ChatService(private val chatRepository: ChatRepository) {
    fun getAllChats(): Iterable<Chat> = chatRepository.findAll()

    fun getSingleChat(chatId: String): Chat =
        chatRepository.findById(chatId).orElseThrow { ChatNotFound("Chat with id $chatId not found") }

    fun getPlatformChats(platform: Platform): Iterable<Chat> = chatRepository.findAllByPlatform(platform)
}