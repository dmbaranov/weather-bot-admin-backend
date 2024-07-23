package org.weatherbot.admin.chat

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.weatherbot.admin.model.Chat

@Repository
interface ChatRepository : CrudRepository<Chat, String> {
    fun findAllByPlatform(platform: Platform): List<Chat>
}