package br.com.senac.biblioteca.repository;

import br.com.senac.biblioteca.model.Livro;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends PagingAndSortingRepository<Livro, Long> {
}
