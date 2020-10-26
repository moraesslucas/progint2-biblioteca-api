package br.com.senac.biblioteca.service;

import br.com.senac.biblioteca.dto.LivroDto;
import br.com.senac.biblioteca.enumeration.CodeEnum;
import br.com.senac.biblioteca.exception.ResourceNotFoundException;
import br.com.senac.biblioteca.model.Livro;
import br.com.senac.biblioteca.repository.LivroRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
    public Page<LivroDto> findAll(Pageable pageable) {
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
        livroRepository.deleteById(id);
    }
}
