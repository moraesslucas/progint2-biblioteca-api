package br.com.senac.biblioteca.exception;

import br.com.senac.biblioteca.enumeration.CodeEnum;

public class EmprestimoViolationException extends CodeMessageException {

    public EmprestimoViolationException(String message, CodeEnum code) {
        super(message, code);
    }
}
