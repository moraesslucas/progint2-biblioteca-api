package br.com.senac.biblioteca.service;

import br.com.senac.biblioteca.dto.CreateFuncionarioDto;
import br.com.senac.biblioteca.dto.FuncionarioDto;
import br.com.senac.biblioteca.dto.UpdateFuncionarioDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FuncionarioService {
    Page<FuncionarioDto> findAll(Pageable pageable);
    FuncionarioDto findById(long matricula);
    void delete(long matricula);
    FuncionarioDto create(CreateFuncionarioDto funcionarioDto);
    FuncionarioDto update(long matricula, UpdateFuncionarioDto funcionarioDto);
}
