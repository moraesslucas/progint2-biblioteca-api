package br.com.senac.biblioteca.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateFuncionarioDto {
    @NotBlank(message = "Nome não pode ser nulo")
    @Size(message = "Nome pode ter até 255 caracteres", max = 255)
    private String nome;

    @NotBlank(message = "Telefone não pode ser nulo")
    @Pattern(regexp = "\\(\\d{2,}\\) \\d{4,}-\\d{4}", message = "O campo telefone deve ter o formato (##) ####-#### ou (##) #####-####")
    private String telefone;

    @Pattern(regexp = "^(|.{6,40})$", message = "A senha deve conter entre 6 e 40 caracteres")
    private String senha;
}
