package org.weatherbot.admin.chat_config

import org.springframework.web.bind.annotation.*

@RestController
class ChatConfigController(val chatConfigService: ChatConfigService) {
    @GetMapping("/v1/chatconfig/{chatId}")
    fun getChatConfig(@PathVariable chatId: String) = chatConfigService.getChatConfig(chatId)

    @PostMapping("/v1/chatconfig/{chatId}")
    fun createChatConfig(@PathVariable chatId: String): ChatConfig =
        chatConfigService.createChatConfig(chatId)

    @PutMapping("/v1/chatconfig")
    fun updateChatConfig(@RequestBody chatConfig: ChatConfig): ChatConfig =
        chatConfigService.updateChatConfig(chatConfig)
}
