package br.com.senac.biblioteca.exception;

import br.com.senac.biblioteca.dto.ErrorDto;
import br.com.senac.biblioteca.dto.ExceptionResponse;
import br.com.senac.biblioteca.enumeration.CodeEnum;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionResponse handleNotFound(ResourceNotFoundException resourceNotFoundException) {
        return ExceptionResponse.builder()
                .errors(List.of(
                        ErrorDto.builder()
                                .code(resourceNotFoundException.getCode())
                                .message(resourceNotFoundException.getMessage())
                                .build()
                        )
                )
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    public ExceptionResponse handleNotFound(BadRequestException badRequestException) {
        return ExceptionResponse.builder()
                .errors(List.of(
                        ErrorDto.builder()
                                .code(badRequestException.getCode())
                                .message(badRequestException.getMessage())
                                .build()
                        )
                )
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    @ExceptionHandler({EmprestimoViolationException.class, AlreadyRemovedException.class})
    public ExceptionResponse handleEmprestimoException(CodeMessageException emprestimoViolationException) {
        return ExceptionResponse.builder()
                .errors(List.of(
                        ErrorDto.builder()
                                .code(emprestimoViolationException.getCode())
                                .message(emprestimoViolationException.getMessage())
                                .build()
                        )
                )
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(InvalidRoleException.class)
    public ExceptionResponse handleRoleException(InvalidRoleException invalidRoleException) {
        return ExceptionResponse.builder()
                .errors(List.of(
                        ErrorDto.builder()
                                .code(invalidRoleException.getCode())
                                .message(invalidRoleException.getMessage())
                                .build()
                        )
                )
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ExceptionResponse handleNotReadable() {
        return ExceptionResponse.builder()
                .errors(List.of(
                        ErrorDto.builder()
                                .code(CodeEnum.BAD_REQUEST)
                                .message("A requisição enviada está mal formada, valide os parâmetros de entrada")
                                .build()
                        )
                )
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponse handleInvalidBody(MethodArgumentNotValidException methodArgumentNotValidException) {
        return ExceptionResponse.builder()
                .errors(methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
                        .map(fieldError -> ErrorDto.builder()
                                .code(CodeEnum.INVALID_FIELD)
                                .message(fieldError.getDefaultMessage())
                                .build()
                        )
                        .collect(Collectors.toList())
                )
                .build();
    }

}
