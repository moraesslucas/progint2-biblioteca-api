package br.com.senac.biblioteca.controller;

import br.com.senac.biblioteca.dto.CreateEmprestimoDto;
import br.com.senac.biblioteca.dto.EmprestimoDto;
import br.com.senac.biblioteca.dto.ExceptionResponse;
import br.com.senac.biblioteca.dto.LivroDto;
import br.com.senac.biblioteca.service.EmprestimoService;
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
@RequestMapping("/emprestimo")
@Validated
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @GetMapping
    @ApiOperation("Realizar a busca dos empréstimos existentes na biblioteca")
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
    public ResponseEntity<Page<EmprestimoDto>> getEmprestimos(@RequestParam(value = "ativo", required = false) boolean ativo, Pageable pageable) {
        return ResponseEntity.ok(emprestimoService.findAll(pageable, ativo));
    }

    @PostMapping
    @ApiOperation("Realizar o cadastro de um empréstimo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "O cadastro do empréstimo foi criado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida, valide os parâmetros de entrada", response = ExceptionResponse.class)
    })
    public ResponseEntity<EmprestimoDto> createLivro(@Valid @RequestBody CreateEmprestimoDto emprestimoDto) {
        var emprestimo = emprestimoService.create(emprestimoDto);

        var uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/emprestimo/{id}")
                .buildAndExpand(emprestimo.getId())
                .toUri();

        return ResponseEntity.created(uri).body(emprestimo);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Devolver um empréstimo realizado")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "A devolução foi realizada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida, valide os parâmetros de entrada", response = ExceptionResponse.class),
            @ApiResponse(code = 404, message = "Empréstimo não localizado", response = ExceptionResponse.class)
    })
    public ResponseEntity<Void> devolver(@PathVariable("id") long id) {
        emprestimoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
