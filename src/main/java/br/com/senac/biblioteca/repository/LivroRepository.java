package br.com.senac.biblioteca.repository;

import br.com.senac.biblioteca.model.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends PagingAndSortingRepository<Livro, Long> {

    @Query(value = "SELECT l FROM Livro l LEFT JOIN l.emprestimos e WHERE e.id IS NULL AND e.dataPrevistaEntrega IS NULL")
    Page<Livro> findAllDisponiveis(Pageable pageable);
}
