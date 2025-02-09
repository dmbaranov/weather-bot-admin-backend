package org.weatherbot.admin.statistics

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "command_statistics")
class Statistics(
    @Id
    val id: Long,
    val command: String,
    val timestamp: String,
    val chatId: String,
    val botUserId: String
)
