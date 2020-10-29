package br.com.senac.biblioteca.controller;

import br.com.senac.biblioteca.dto.ClienteDto;
import br.com.senac.biblioteca.dto.ExceptionResponse;
import br.com.senac.biblioteca.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
@Validated
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    @ApiOperation("Realizar a busca dos clientes existentes na biblioteca")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A requisição foi processada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida, valide os parâmetros de entrada", response = ExceptionResponse.class)
    })
    public ResponseEntity<Page<ClienteDto>> getClientes(Pageable pageable,
                                                        @RequestParam(value = "disponivel", required = false)
                                                                boolean disponiveis) {
        return ResponseEntity.ok(clienteService.findAll(pageable, disponiveis));
    }

    @GetMapping("/{matricula}")
    @ApiOperation("Realizar a busca de um cliente por matricula")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "O cliente foi localizado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida, valide os parâmetros de entrada", response = ExceptionResponse.class),
            @ApiResponse(code = 404, message = "Cliente não encontrado, válide o id informado", response = ExceptionResponse.class)
    })
    public ResponseEntity<ClienteDto> getCliente(@PathVariable("matricula") long matricula) {
        return ResponseEntity.ok(clienteService.findById(matricula));
    }

    @DeleteMapping("/{matricula}")
    @ApiOperation("Remover um cliente por matricula")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "A remoção foi realizada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida, valide os parâmetros de entrada", response = ExceptionResponse.class),
            @ApiResponse(code = 404, message = "Cliente não localizado", response = ExceptionResponse.class)
    })
    public ResponseEntity<Void> deleteCliente(@PathVariable("matricula") long matricula) {
        clienteService.delete(matricula);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ApiOperation("Realizar o cadastro de um cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "O cadastro do cliente foi criado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida, valide os parâmetros de entrada", response = ExceptionResponse.class)
    })
    public ResponseEntity<ClienteDto> createCliente(@Valid @RequestBody ClienteDto clienteDto) {
        var cliente = clienteService.create(clienteDto);

        var uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/cliente/{matricula}")
                .buildAndExpand(cliente.getMatricula())
                .toUri();

        return ResponseEntity.created(uri).body(cliente);
    }

    @PutMapping("/{matricula}")
    @ApiOperation("Realizar a atualização de um cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "O cadastro do cliente foi atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida, valide os parâmetros de entrada", response = ExceptionResponse.class)
    })
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable("matricula") long matricula, @Valid @RequestBody ClienteDto clienteDto) {
        var cliente = clienteService.update(matricula, clienteDto);

        var uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/cliente/{matricula}")
                .buildAndExpand(matricula)
                .toUri();

        return ResponseEntity.created(uri).body(cliente);
    }
}
