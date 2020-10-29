package br.com.senac.biblioteca.service;

import br.com.senac.biblioteca.model.Funcionario;
import br.com.senac.biblioteca.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
        Funcionario funcionario = funcionarioRepository.findById(Long.parseLong(matricula))
                .orElseThrow(() -> new UsernameNotFoundException("Funcionário não localizado com a matrícula: " + matricula));

        return UserDetailsImpl.build(funcionario);
    }
}
