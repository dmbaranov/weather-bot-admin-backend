package org.weatherbot.admin.chat

data class SendMessageDto(val chatId: String, val message: String)

data class UpdateChatSwearwordsDto(val chatId: String, val swearwordsConfig: ChatSwearwords)