package br.com.senac.biblioteca.exception;

import br.com.senac.biblioteca.enumeration.CodeEnum;

public class BadRequestException extends CodeMessageException {

    public BadRequestException(String message, CodeEnum code) {
        super(message, code);
    }

}
