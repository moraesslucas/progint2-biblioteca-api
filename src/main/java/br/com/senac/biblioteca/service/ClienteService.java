package br.com.senac.biblioteca.service;

import br.com.senac.biblioteca.dto.ClienteDto;
import br.com.senac.biblioteca.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {

    ClienteDto findById(long matricula);
    Page<ClienteDto> findAll(Pageable pageable);
    ClienteDto create(ClienteDto cliente);
    ClienteDto update(long matricula, ClienteDto cliente);
    void delete(long matricula);
    Cliente findIfAvailable(long id);

}
