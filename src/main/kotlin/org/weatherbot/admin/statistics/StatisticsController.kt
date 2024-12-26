package org.weatherbot.admin.statistics

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class StatisticsController(private val statisticsService: StatisticsService) {
    @GetMapping("/v1/statistics/{chatId}")
    fun getChatStatistics(@PathVariable chatId: String) = statisticsService.getChatStatistics(chatId)
}