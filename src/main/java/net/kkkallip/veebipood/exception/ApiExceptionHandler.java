package net.kkkallip.veebipood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(RuntimeException exception) {
        ErrorMessage message = new ErrorMessage();
        message.setMessage(exception.getMessage());
        message.setStatus(HttpStatus.BAD_REQUEST.value());
        message.setTimeStamp(new Date());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
