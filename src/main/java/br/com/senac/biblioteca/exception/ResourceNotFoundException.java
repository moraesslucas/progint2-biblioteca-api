package br.com.senac.biblioteca.exception;

import br.com.senac.biblioteca.enumeration.CodeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends CodeMessageException {

    public ResourceNotFoundException(String message, CodeEnum code) {
        super(message, code);
    }

}
