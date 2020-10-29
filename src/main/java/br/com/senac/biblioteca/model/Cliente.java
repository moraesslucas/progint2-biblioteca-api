package br.com.senac.biblioteca.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CLIENTE")
@Getter
@Setter
@EqualsAndHashCode
public class Cliente {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "MATRICULA")
    private long matricula;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEFONE")
    private String telefone;

    @OneToMany(mappedBy = "cliente")
    private List<Emprestimo> emprestimos;

}
