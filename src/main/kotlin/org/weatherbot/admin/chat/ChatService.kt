package org.weatherbot.admin.chat

import org.springframework.stereotype.Service
import java.util.*

@Service
class ChatService(private val chatRepository: ChatRepository) {
    fun getAllChats(): Iterable<Chat> = chatRepository.findAll()

    fun getSingleChat(chatId: String): Optional<Chat> = chatRepository.findById(chatId)

    fun getPlatformChats(platform: Platform): Iterable<Chat> = chatRepository.findAllByPlatform(platform)
}