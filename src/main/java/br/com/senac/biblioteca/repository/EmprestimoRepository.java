package br.com.senac.biblioteca.repository;

import br.com.senac.biblioteca.model.Emprestimo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends PagingAndSortingRepository<Emprestimo, Long> {

    Page<Emprestimo> findAllByDataEfetivaEntregaIsNull(Pageable pageable);

}
