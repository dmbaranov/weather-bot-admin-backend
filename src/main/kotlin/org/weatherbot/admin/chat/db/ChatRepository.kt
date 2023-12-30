package org.weatherbot.admin.chat.db

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRepository : CrudRepository<Chat, String> {
    override fun findAll(): List<Chat>
}