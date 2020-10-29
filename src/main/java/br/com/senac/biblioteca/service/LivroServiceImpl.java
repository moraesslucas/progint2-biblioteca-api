package br.com.senac.biblioteca.service;

import br.com.senac.biblioteca.dto.LivroDto;
import br.com.senac.biblioteca.enumeration.CodeEnum;
import br.com.senac.biblioteca.exception.AlreadyRemovedException;
import br.com.senac.biblioteca.exception.BadRequestException;
import br.com.senac.biblioteca.exception.EmprestimoViolationException;
import br.com.senac.biblioteca.exception.ResourceNotFoundException;
import br.com.senac.biblioteca.model.Livro;
import br.com.senac.biblioteca.repository.LivroRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository;
    private final ModelMapper modelMapper;

    @Override
    public LivroDto findById(long id) {
        return modelMapper.map(
                livroRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado", CodeEnum.NOT_FOUND)),
                LivroDto.class);
    }

    @Override
    public Page<LivroDto> findAll(Pageable pageable, boolean disponiveis) {
        return livroRepository.findAll(pageable).map(livro -> modelMapper.map(livro, LivroDto.class));
    }

    @Override
    public LivroDto create(LivroDto livro) {
        return modelMapper.map(livroRepository.save(modelMapper.map(livro, Livro.class)), LivroDto.class);
    }

    @Override
    public LivroDto update(long id, LivroDto livro) {
        livro.setId(id);
        return create(livro);
    }

    @Override
    public void delete(long id) {
        try {
            livroRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new AlreadyRemovedException("Livro já foi removido", CodeEnum.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            throw new EmprestimoViolationException("O livro não pode ser removido, pois tem um empréstimo ativo", CodeEnum.BAD_REQUEST);
        }
    }

    @Override
    public Livro findIfAvailable(long id) {
        var livro = livroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado", CodeEnum.NOT_FOUND));

        if (livro.getEmprestimos().stream().anyMatch(emprestimo -> emprestimo.getDataEfetivaEntrega() == null)) {
            throw new BadRequestException("Livro não disponível", CodeEnum.INVALID_FIELD);
        }

        return livro;
    }
}
