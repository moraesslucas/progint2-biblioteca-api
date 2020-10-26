package br.com.senac.biblioteca.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {

    private long matricula;
    private String nome;
    private String email;
    private String telefone;

}
