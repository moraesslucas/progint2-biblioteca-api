package br.com.senac.biblioteca.model;

import br.com.senac.biblioteca.enumeration.RoleEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleEnum nome;

}
