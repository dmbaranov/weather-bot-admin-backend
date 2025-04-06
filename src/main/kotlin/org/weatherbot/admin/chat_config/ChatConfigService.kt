package org.weatherbot.admin.chat_config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Service
import org.weatherbot.admin.chat.ChatRepository

@Service
class ChatConfigService(
    private val chatConfigRepository: ChatConfigRepository,
    private val chatRepository: ChatRepository
) {
    fun getChatConfig(chatId: String): ChatConfig? = chatConfigRepository.findByChatId(chatId).orElseThrow {
        ChatConfigNotFound("Config for chat $chatId not found")
    }

    fun createChatConfig(chatId: String): ChatConfig {
        chatRepository.findById(chatId).orElseThrow {
            ChatConfigInvalid("Chat $chatId does not exist")
        }

        chatConfigRepository.findByChatId(chatId).ifPresent {
            throw ChatConfigInvalid("Config for chat $chatId already exists")
        }

        return chatConfigRepository.save(ChatConfig(chatId = chatId, config = "{}"))
    }

    fun updateChatConfig(chatConfig: ChatConfig): ChatConfig {
        val existingConfig = chatConfigRepository.findByChatId(chatConfig.chatId).orElseThrow {
            ChatConfigInvalid("Config for chat ${chatConfig.chatId} does not exist")
        }

        try {
            jacksonObjectMapper().readTree(chatConfig.config)
        } catch (_: Exception) {
            throw ChatConfigInvalid("Config is not valid")
        }

        existingConfig.config = chatConfig.config

        return chatConfigRepository.save(existingConfig)
    }
}
