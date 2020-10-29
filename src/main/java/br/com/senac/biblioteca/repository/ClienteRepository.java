package br.com.senac.biblioteca.repository;

import br.com.senac.biblioteca.model.Cliente;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {
}
