package br.com.senac.biblioteca.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ClienteDto {

    private long matricula;

    @NotBlank(message = "O campo nome deve ser informado")
    private String nome;

    @NotBlank(message = "O campo email deve ser informado")
    private String email;

    @NotBlank(message = "O campo telefone deve ser informado")
    private String telefone;

}
