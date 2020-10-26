package br.com.senac.biblioteca.exception;

import br.com.senac.biblioteca.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionResponse handleNotFound(ResourceNotFoundException resourceNotFoundException) {
        return ExceptionResponse.builder()
                .code(resourceNotFoundException.getCode())
                .message(resourceNotFoundException.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
