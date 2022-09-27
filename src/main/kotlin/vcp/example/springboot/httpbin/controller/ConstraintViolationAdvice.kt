package vcp.example.springboot.httpbin.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ConstraintViolationAdvice {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleArgumentNotValid(e: MethodArgumentNotValidException): ResponseEntity<ErrorBody> {

        val errorFieldList = e.bindingResult.fieldErrors.map {
            ErrorField(it.field, it.defaultMessage)
        }
        return ResponseEntity(ErrorBody(errorFieldList), HttpStatus.BAD_REQUEST)
    }

    data class ErrorBody(val violation: List<ErrorField>)

    data class ErrorField(val field: String, val message: String?)
}