package br.com.senac.biblioteca.service;

import br.com.senac.biblioteca.dto.CreateEmprestimoDto;
import br.com.senac.biblioteca.dto.EmprestimoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmprestimoService {

    Page<EmprestimoDto> findAll(Pageable pageable, boolean ativo);
    void delete(long id);
    EmprestimoDto create(CreateEmprestimoDto emprestimoDto);
}
