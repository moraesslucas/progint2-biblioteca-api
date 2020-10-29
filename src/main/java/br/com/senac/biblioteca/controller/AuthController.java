package br.com.senac.biblioteca.controller;

import br.com.senac.biblioteca.dto.JwtResponse;
import br.com.senac.biblioteca.dto.LoginRequest;
import br.com.senac.biblioteca.repository.FuncionarioRepository;
import br.com.senac.biblioteca.repository.RoleRepository;
import br.com.senac.biblioteca.security.Base64Utils;
import br.com.senac.biblioteca.security.JwtUtils;
import br.com.senac.biblioteca.service.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    AuthenticationManager authenticationManager;
    FuncionarioRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder encoder;
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestHeader("Authorization") String authorization) {
        var loginRequest = Base64Utils.parseAuthorizationHeader(authorization);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        var userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(
                JwtResponse.builder()
                        .username(userDetails.getUsername())
                        .nome(userDetails.getNome())
                        .accessToken(jwt)
                        .build()
        );
    }

}