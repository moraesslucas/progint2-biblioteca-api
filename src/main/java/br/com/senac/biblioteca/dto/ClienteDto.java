package br.com.senac.biblioteca.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class ClienteDto {

    private long matricula;

    @NotBlank(message = "O campo nome deve ser informado")
    private String nome;

    @NotBlank(message = "O campo e-mail deve ser informado")
    @Email(message = "O campo e-mail deve ter um formato válido de e-mail")
    private String email;

    @NotBlank(message = "O campo telefone deve ser informado")
    @Pattern(regexp = "\\(\\d{2,}\\) \\d{4,}-\\d{4}", message = "O campo telefone deve ter o formato (##) ####-#### ou (##) #####-####")
    private String telefone;

}
