package org.weatherbot.admin.chat_config

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ChatConfigRepository : CrudRepository<ChatConfig, String> {
    fun findByChatId(chatId: String): Optional<ChatConfig>
}
