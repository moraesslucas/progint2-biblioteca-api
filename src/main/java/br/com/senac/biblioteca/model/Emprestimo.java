package br.com.senac.biblioteca.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "EMPRESTIMO")
@Getter
@Setter
@EqualsAndHashCode
public class Emprestimo {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID")
    private long id;

    @Column(name = "DATA_EMPRESTIMO")
    private LocalDate dataEmprestimo;

    @Column(name = "DATA_PREVISTA_ENTREGA")
    private LocalDate dataPrevistaEntrega;

    @Column(name = "DATA_EFETIVA_ENTREGA")
    private LocalDate dataEfetivaEntrega;

    @ManyToOne
    @JoinColumn(name = "ID_LIVRO")
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "MATRICULA_CLIENTE", referencedColumnName = "MATRICULA")
    private Cliente cliente;


    public boolean isAtivo() {
        return dataEfetivaEntrega == null;
    }
}
