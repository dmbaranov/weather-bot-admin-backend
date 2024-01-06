package org.weatherbot.admin.chat

import org.springframework.stereotype.Service
import org.weatherbot.admin.chat.db.Chat
import org.weatherbot.admin.chat.db.ChatRepository
import java.util.*

@Service
class ChatService(private val chatRepository: ChatRepository) {
    fun getAllChats(): Iterable<Chat> = chatRepository.findAll()
    fun getSingleChat(chatId: String): Optional<Chat> = chatRepository.findById(chatId)
}