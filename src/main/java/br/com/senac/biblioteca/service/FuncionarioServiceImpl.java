package br.com.senac.biblioteca.service;

import br.com.senac.biblioteca.dto.CreateFuncionarioDto;
import br.com.senac.biblioteca.dto.FuncionarioDto;
import br.com.senac.biblioteca.enumeration.CodeEnum;
import br.com.senac.biblioteca.enumeration.RoleEnum;
import br.com.senac.biblioteca.exception.AlreadyRemovedException;
import br.com.senac.biblioteca.exception.InvalidRoleException;
import br.com.senac.biblioteca.exception.ResourceNotFoundException;
import br.com.senac.biblioteca.model.Funcionario;
import br.com.senac.biblioteca.repository.FuncionarioRepository;
import br.com.senac.biblioteca.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<FuncionarioDto> findAll(Pageable pageable) {
        return funcionarioRepository.findAll(pageable).map(funcionario -> modelMapper.map(funcionario, FuncionarioDto.class));
    }

    @Override
    public FuncionarioDto findById(long matricula) {
        return modelMapper.map(
                funcionarioRepository.findById(matricula)
                        .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado", CodeEnum.NOT_FOUND)),
                FuncionarioDto.class);
    }

    @Override
    public void delete(long matricula) {
        try {
            funcionarioRepository.deleteById(matricula);
        } catch (EmptyResultDataAccessException e) {
            throw new AlreadyRemovedException("Funcionário já foi removido", CodeEnum.NOT_FOUND);
        }
    }

    @Override
    public FuncionarioDto create(CreateFuncionarioDto funcionarioDto) {
        var funcionario = modelMapper.map(funcionarioDto, Funcionario.class);

        funcionario.setSenha(passwordEncoder.encode(funcionarioDto.getSenha()));
        funcionario.setRoles(
                Set.of(roleRepository.findByNome(RoleEnum.ROLE_USER)
                        .orElseThrow(() -> new InvalidRoleException("Problema ao setar permissões do usuário", CodeEnum.INVALID_ROLE)))
        );

        return modelMapper.map(funcionarioRepository.save(funcionario), FuncionarioDto.class);
    }

    @Override
    public FuncionarioDto update(long matricula, CreateFuncionarioDto funcionarioDto) {
        var funcionario = modelMapper.map(funcionarioDto, Funcionario.class);

        funcionario.setSenha(passwordEncoder.encode(funcionarioDto.getSenha()));
        funcionario.setRoles(
                Set.of(roleRepository.findByNome(RoleEnum.ROLE_USER)
                        .orElseThrow(() -> new InvalidRoleException("Problema ao setar permissões do usuário", CodeEnum.INVALID_ROLE)))
        );
        funcionario.setMatricula(matricula);

        return modelMapper.map(funcionarioRepository.save(funcionario), FuncionarioDto.class);
    }
}
