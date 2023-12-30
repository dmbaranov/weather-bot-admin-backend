package org.weatherbot.admin.dashboard

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class DashboardController(val dashboardService: DashboardService) {
    @GetMapping("/")
    fun getAllUsers() = dashboardService.getAllUsers()

    @GetMapping("/{userId}")
    fun getUserById(@PathVariable("userId") userId: String) = dashboardService.getUserById(userId)
}