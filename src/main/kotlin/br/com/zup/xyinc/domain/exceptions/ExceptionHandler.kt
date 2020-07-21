package br.com.zup.xyinc.domain.exceptions

import br.com.zup.xyinc.domain.dtos.Exception
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant
import java.time.format.DateTimeFormatter


@ControllerAdvice
class ExceptionHandler<T> {

    @ExceptionHandler(value = [(BadRequestException::class)])
    fun handlerException(e: BadRequestException): ResponseEntity<Exception> {
        e.messages
        return ResponseEntity(Exception(HttpStatus.BAD_REQUEST,
                DateTimeFormatter.ISO_INSTANT.format(Instant.now()),
                e.messages.map { it.toString() }), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [(HttpMessageNotReadableException::class)])
    fun handlerException(e: HttpMessageNotReadableException): ResponseEntity<Exception> {
        return ResponseEntity(Exception(HttpStatus.BAD_REQUEST,
                DateTimeFormatter.ISO_INSTANT.format(Instant.now()),
                listOf(e.message.toString())), HttpStatus.NOT_FOUND)
    }
}