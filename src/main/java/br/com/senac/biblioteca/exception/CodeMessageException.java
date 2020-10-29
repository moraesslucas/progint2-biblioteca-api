package br.com.senac.biblioteca.exception;

import br.com.senac.biblioteca.enumeration.CodeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeMessageException extends RuntimeException {

    private String message;
    private CodeEnum code;

    public CodeMessageException(String message, CodeEnum code) {
        this.message = message;
        this.code = code;
    }

}
