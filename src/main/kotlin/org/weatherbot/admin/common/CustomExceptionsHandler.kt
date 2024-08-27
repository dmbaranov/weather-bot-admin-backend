package org.weatherbot.admin.common

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

data class ApiErrorResponse(val status: Int, val error: String, val message: String, val timestamp: Long)

@ControllerAdvice
class CustomExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadable(e: HttpMessageNotReadableException): ResponseEntity<ApiErrorResponse> {
        val cause = e.cause;

        if (cause is UnrecognizedPropertyException) {
            return ResponseEntity(
                buildErrorResponse("Unrecognized field: \"${cause.propertyName}\""),
                HttpStatus.BAD_REQUEST
            )
        }

        return ResponseEntity(buildErrorResponse("Invalid input"), HttpStatus.BAD_REQUEST)
    }
}

fun buildErrorResponse(message: String) = ApiErrorResponse(
    status = HttpStatus.BAD_REQUEST.value(),
    error = HttpStatus.BAD_REQUEST.reasonPhrase,
    message = message,
    timestamp = System.currentTimeMillis()
)