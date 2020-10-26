package br.com.senac.biblioteca.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LIVRO")
@Getter
@Setter
@EqualsAndHashCode
public class Livro {

    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "ISBN")
    private String ISBN;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "AUTOR")
    private String autor;

    @Column(name = "EDITORA")
    private String editora;

    @Column(name = "ANO_PUBLICACAO")
    private int anoPublicacao;

}
