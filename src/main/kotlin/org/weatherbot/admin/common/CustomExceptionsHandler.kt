package org.weatherbot.admin.common

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

data class ApiErrorResponse(val status: Int, val error: String, val message: String, val timestamp: Long)

@ControllerAdvice
class CustomExceptionHandler {
    @ExceptionHandler(UnrecognizedPropertyException::class)
    fun handleUnrecognizedPropertyException(e: UnrecognizedPropertyException): ResponseEntity<ApiErrorResponse> {
        val errorMessage = "Unrecognized field: \"${e.propertyName}\""

        val errorResponse = ApiErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.reasonPhrase,
            message = errorMessage,
            timestamp = System.currentTimeMillis()
        )

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}