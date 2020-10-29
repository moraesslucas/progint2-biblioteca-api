package br.com.senac.biblioteca.repository;

import br.com.senac.biblioteca.enumeration.RoleEnum;
import br.com.senac.biblioteca.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByNome(RoleEnum name);

}
