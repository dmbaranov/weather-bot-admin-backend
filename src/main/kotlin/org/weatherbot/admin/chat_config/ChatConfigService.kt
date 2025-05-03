package org.weatherbot.admin.chat_config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Service
import org.weatherbot.admin.chat.ChatRepository
import org.weatherbot.admin.messaging.MessagingService

@Service
class ChatConfigService(
    private val chatConfigRepository: ChatConfigRepository,
    private val chatRepository: ChatRepository,
    private val messagingService: MessagingService
) {
    fun getChatConfig(chatId: String): ChatConfig? = chatConfigRepository.findByChatId(chatId).orElseThrow {
        ChatConfigNotFound("Config for chat $chatId not found")
    }

    fun createChatConfig(chatId: String): ChatConfig {
        val chat = chatRepository.findById(chatId).orElseThrow {
            ChatConfigInvalid("Chat $chatId does not exist")
        }

        chatConfigRepository.findByChatId(chatId).ifPresent {
            throw ChatConfigInvalid("Config for chat $chatId already exists")
        }

        messagingService.send(chat.platform, ChatConfigRoutingKey.UPDATED, mapOf("chatId" to chatId));
        return chatConfigRepository.save(ChatConfig(chatId = chatId, config = "{}"))
    }

    fun updateChatConfig(chatConfig: ChatConfig): ChatConfig {
        val existingConfig = chatConfigRepository.findByChatId(chatConfig.chatId).orElseThrow {
            ChatConfigInvalid("Config for chat ${chatConfig.chatId} does not exist")
        }

        val chat = chatRepository.findById(chatConfig.chatId).orElseThrow {
            ChatConfigInvalid("Chat ${chatConfig.chatId} does not exist")
        };

        try {
            jacksonObjectMapper().readTree(chatConfig.config)
        } catch (_: Exception) {
            throw ChatConfigInvalid("Config is not valid")
        }

        messagingService.send(chat.platform, ChatConfigRoutingKey.UPDATED, mapOf("chatId" to chatConfig.chatId))
        existingConfig.config = chatConfig.config

        return chatConfigRepository.save(existingConfig)
    }
}
