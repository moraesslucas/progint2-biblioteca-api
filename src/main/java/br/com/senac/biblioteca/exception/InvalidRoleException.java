package br.com.senac.biblioteca.exception;

import br.com.senac.biblioteca.enumeration.CodeEnum;

public class InvalidRoleException extends CodeMessageException {
    public InvalidRoleException(String message, CodeEnum code) {
        super(message, code);
    }
}
