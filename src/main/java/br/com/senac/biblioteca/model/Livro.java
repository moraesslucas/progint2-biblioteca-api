package br.com.senac.biblioteca.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "LIVRO")
@Getter
@Setter
@EqualsAndHashCode
public class Livro {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
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

    @OneToMany(mappedBy = "livro")
    private List<Emprestimo> emprestimos;

    public boolean isDisponivel() {
        return emprestimos.isEmpty();
    }

}
