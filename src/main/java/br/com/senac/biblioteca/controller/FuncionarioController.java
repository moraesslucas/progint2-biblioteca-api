package br.com.senac.biblioteca.controller;

import br.com.senac.biblioteca.dto.CreateFuncionarioDto;
import br.com.senac.biblioteca.dto.ExceptionResponse;
import br.com.senac.biblioteca.dto.FuncionarioDto;
import br.com.senac.biblioteca.service.FuncionarioService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/funcionario")
@Validated
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @GetMapping
    @ApiOperation("Realizar a busca dos funcionarios existentes na biblioteca")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A requisição foi processada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida, valide os parâmetros de entrada", response = ExceptionResponse.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Número da página que você deseja retornar"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Tamanho de registros da página."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Parâmetro de ordenação no seguinte padrão: propriedade(,asc|desc). " +
                            "A ordenação padrão é asc. " +
                            "Você pode enviar várias vezes o parâmetro sort para múltiplos campos." +
                            "Exemplo: ?sort=id,asc&sort=data,asc&sort=prop,desc")
    })
    public ResponseEntity<Page<FuncionarioDto>> getFuncionarios(Pageable pageable) {
        return ResponseEntity.ok(funcionarioService.findAll(pageable));
    }

    @GetMapping("/{matricula}")
    @ApiOperation("Realizar a busca de um funcionario por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "O funcionario foi localizado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida, valide os parâmetros de entrada", response = ExceptionResponse.class),
            @ApiResponse(code = 404, message = "Funcionario não encontrado, válide o id informado", response = ExceptionResponse.class)
    })
    public ResponseEntity<FuncionarioDto> getFuncionario(@PathVariable("matricula") long matricula) {
        return ResponseEntity.ok(funcionarioService.findById(matricula));
    }

    @DeleteMapping("/{matricula}")
    @ApiOperation("Remover um funcionario por matrícula")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "A remoção foi realizada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida, valide os parâmetros de entrada", response = ExceptionResponse.class),
            @ApiResponse(code = 404, message = "Funcionario não localizado", response = ExceptionResponse.class)
    })
    public ResponseEntity<Void> deleteFuncionario(@PathVariable("matricula") long matricula) {
        funcionarioService.delete(matricula);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ApiOperation("Realizar o cadastro de um funcionario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "O cadastro do funcionario foi criado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida, valide os parâmetros de entrada", response = ExceptionResponse.class)
    })
    public ResponseEntity<FuncionarioDto> createFuncionario(@Valid @RequestBody CreateFuncionarioDto funcionarioDto) {
        var funcionario = funcionarioService.create(funcionarioDto);

        var uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/funcionario/{matricula}")
                .buildAndExpand(funcionario.getMatricula())
                .toUri();

        return ResponseEntity.created(uri).body(funcionario);
    }

    @PutMapping("/{matricula}")
    @ApiOperation("Realizar a atualização de um funcionario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "O cadastro do funcionario foi atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida, valide os parâmetros de entrada", response = ExceptionResponse.class)
    })
    public ResponseEntity<FuncionarioDto> updateFuncionario(@PathVariable("matricula") long matricula, @Valid @RequestBody CreateFuncionarioDto funcionarioDto) {
        var funcionario = funcionarioService.update(matricula, funcionarioDto);

        var uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/funcionario/{matricula}")
                .buildAndExpand(matricula)
                .toUri();

        return ResponseEntity.created(uri).body(funcionario);
    }
}