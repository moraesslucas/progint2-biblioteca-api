package br.com.senac.biblioteca.repository;

import br.com.senac.biblioteca.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {

    @Query(value = "SELECT DISTINCT c FROM Cliente c LEFT JOIN c.emprestimos e WHERE e.dataEfetivaEntrega IS NULL GROUP BY c.matricula HAVING COUNT(e) < 3")
    Page<Cliente> findAllDisponiveis(Pageable pageable);
}
