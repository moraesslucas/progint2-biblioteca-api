package br.com.senac.biblioteca.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDto {

    private long id;
    private String ISBN;
    private String nome;
    private String autor;
    private String editora;
    private int anoPublicacao;

}
