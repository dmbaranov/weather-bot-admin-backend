package org.weatherbot.admin.chat

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ChatNotFound(message: String) : RuntimeException(message)