package br.com.senac.biblioteca.service;

import br.com.senac.biblioteca.dto.LivroDto;
import br.com.senac.biblioteca.model.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LivroService {

    LivroDto findById(long id);
    Page<LivroDto> findAll(Pageable pageable, boolean disponiveis);
    LivroDto create(LivroDto livro);
    LivroDto update(long id, LivroDto livro);
    void delete(long id);
    Livro findIfAvailable(long idLivro);

}
