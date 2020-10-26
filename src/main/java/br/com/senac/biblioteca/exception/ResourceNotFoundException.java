package br.com.senac.biblioteca.exception;

import br.com.senac.biblioteca.enumeration.CodeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    private String message;
    private CodeEnum code;

    public ResourceNotFoundException(String message, CodeEnum code) {
        this.message = message;
        this.code = code;
    }
}
