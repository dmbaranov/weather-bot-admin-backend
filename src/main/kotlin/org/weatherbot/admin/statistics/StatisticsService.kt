package org.weatherbot.admin.statistics

import org.springframework.stereotype.Service

@Service
class StatisticsService(private val statisticsRepository: StatisticsRepository) {
    fun getChatStatistics(chatId: String): Iterable<Statistics> =
        statisticsRepository.findAllByChatId(chatId)
}