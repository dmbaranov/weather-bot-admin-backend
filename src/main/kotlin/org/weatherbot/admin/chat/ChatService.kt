package org.weatherbot.admin.chat

import org.springframework.stereotype.Service
import org.weatherbot.admin.model.Chat
import org.weatherbot.admin.model.ChatMember
import java.util.*

@Service
class ChatService(private val chatRepository: ChatRepository, private val chatMemberRepository: ChatMemberRepository) {
    fun getAllChats(): Iterable<Chat> = chatRepository.findAll()

    fun getSingleChat(chatId: String): Optional<Chat> = chatRepository.findById(chatId)

    fun getChatMembers(chatId: String): Iterable<ChatMember> =
        chatMemberRepository.findChatMembersByIdChatId(chatId);

}