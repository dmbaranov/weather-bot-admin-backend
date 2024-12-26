package org.weatherbot.admin.statistics

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StatisticsRepository : CrudRepository<Statistics, Long> {
    fun findAllByChat_Id(chatId: String): MutableList<Statistics>
}