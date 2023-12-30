package org.weatherbot.admin.chat

import org.springframework.stereotype.Service
import org.weatherbot.admin.chat.db.ChatRepository

@Service
class ChatService(private val chatRepository: ChatRepository) {
    fun getAllChats() = chatRepository.findAll()
}