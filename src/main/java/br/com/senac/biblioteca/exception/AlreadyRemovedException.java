package br.com.senac.biblioteca.exception;

import br.com.senac.biblioteca.enumeration.CodeEnum;

public class AlreadyRemovedException extends CodeMessageException {

    public AlreadyRemovedException(String message, CodeEnum code) {
        super(message, code);
    }

}
