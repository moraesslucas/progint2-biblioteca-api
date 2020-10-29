package br.com.senac.biblioteca.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LivroDto {

    private long id;

    @NotBlank(message = "O campo isbn deve ser informado")
    private String ISBN;

    @NotBlank(message = "O campo nome deve ser informado")
    private String nome;

    @NotBlank(message = "O campo autor deve ser informado")
    private String autor;

    @NotBlank(message = "O campo editora deve ser informado")
    private String editora;

    @NotNull(message = "O campo anoPublicacao deve ser informado")
    @Min(value = 1, message = "O campo ano deve ter valor maior ou igual a 1")
    private int anoPublicacao;

}
