package br.com.senac.biblioteca.dto;

import br.com.senac.biblioteca.enumeration.CodeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorDto {
    private CodeEnum code;
    private String message;
}
