package br.com.senac.biblioteca.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "FUNCIONARIO",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "NOME"),
                @UniqueConstraint(columnNames = "TELEFONE")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATRICULA")
    private long matricula;

    @NotBlank
    @Size(max = 20)
    @Column(name = "NOME")
    private String nome;

    @NotBlank
    @Size(max = 50)
    @Column(name = "TELEFONE")
    private String telefone;

    @NotBlank
    @Size(max = 120)
    @Column(name = "SENHA")
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "FUNCIONARIO_ROLE",
            joinColumns = @JoinColumn(name = "ID_FUNCIONARIO"),
            inverseJoinColumns = @JoinColumn(name = "ID_ROLE"))
    private Set<Role> roles = new HashSet<>();

}