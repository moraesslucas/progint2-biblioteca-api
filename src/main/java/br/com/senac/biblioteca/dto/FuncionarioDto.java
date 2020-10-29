package br.com.senac.biblioteca.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class FuncionarioDto {

    private long matricula;

    @NotBlank
    @Size(max = 255)
    private String nome;

    @NotBlank
    @Pattern(regexp = "\\(\\d{2,}\\) \\d{4,}-\\d{4}", message = "O campo telefone deve ter o formato (##) ####-#### ou (##) #####-####")
    private String telefone;

}