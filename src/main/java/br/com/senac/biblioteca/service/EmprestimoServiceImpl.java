package br.com.senac.biblioteca.service;

import br.com.senac.biblioteca.dto.CreateEmprestimoDto;
import br.com.senac.biblioteca.dto.EmprestimoDto;
import br.com.senac.biblioteca.enumeration.CodeEnum;
import br.com.senac.biblioteca.exception.ResourceNotFoundException;
import br.com.senac.biblioteca.model.Emprestimo;
import br.com.senac.biblioteca.repository.EmprestimoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class EmprestimoServiceImpl implements EmprestimoService {

    private final LivroService livroService;
    private final ClienteService clienteService;
    private final EmprestimoRepository emprestimoRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<EmprestimoDto> findAll(Pageable pageable, boolean ativo) {
        Page<Emprestimo> emprestimos;

        if (ativo) {
            emprestimos = emprestimoRepository.findAllByDataEfetivaEntregaIsNull(pageable);
        } else {
            emprestimos = emprestimoRepository.findAll(pageable);
        }

        return emprestimos.map(emprestimo -> modelMapper.map(emprestimo, EmprestimoDto.class));
    }

    @Override
    public void delete(long id) {
        var emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empréstimo inexistente", CodeEnum.NOT_FOUND));

        emprestimo.setDataEfetivaEntrega(LocalDate.now());
        emprestimoRepository.save(emprestimo);
    }

    @Override
    public EmprestimoDto create(CreateEmprestimoDto emprestimoDto) {
        var emprestimo = new Emprestimo();

        var livro = livroService.findIfAvailable(emprestimoDto.getIdLivro());
        var cliente = clienteService.findIfAvailable(emprestimoDto.getIdCliente());

        emprestimo.setDataEmprestimo(emprestimoDto.getDataEmprestimo());
        emprestimo.setDataPrevistaEntrega(emprestimoDto.getDataEmprestimo().plusDays(7));
        emprestimo.setCliente(cliente);
        emprestimo.setLivro(livro);

        return modelMapper.map(emprestimoRepository.save(emprestimo), EmprestimoDto.class);
    }
}
