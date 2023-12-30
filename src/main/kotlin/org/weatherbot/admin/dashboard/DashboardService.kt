package org.weatherbot.admin.dashboard

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class DashboardService(val db: JdbcTemplate) {
    fun getAllUsers(): List<BotUser> = db.query("select * from bot_user") { res, _ ->
        BotUser(id = res.getString("id"), name = res.getString("name"), isPremium = res.getBoolean("is_premium"))
    }

    fun getUserById(userId: String): BotUser? = getAllUsers().firstOrNull { it.id == userId }
}

data class BotUser(val id: String, val name: String, val isPremium: Boolean)