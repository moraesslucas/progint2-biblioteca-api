package br.com.senac.biblioteca.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateFuncionarioDto {

    @NotBlank
    @Size(max = 255)
    private String nome;

    @NotBlank
    @Size(max = 50)
    private String telefone;

    @NotBlank
    @Size(min = 6, max = 40)
    private String senha;
}
