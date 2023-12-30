package org.weatherbot.admin.chat.db

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.weatherbot.admin.chat.Platform

@Repository
interface ChatRepository : CrudRepository<Chat, String> {
    fun findAllByPlatform(platform: Platform): List<Chat>
}