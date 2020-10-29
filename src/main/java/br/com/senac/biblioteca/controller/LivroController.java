package br.com.senac.biblioteca.controller;

import br.com.senac.biblioteca.dto.ExceptionResponse;
import br.com.senac.biblioteca.dto.LivroDto;
import br.com.senac.biblioteca.service.LivroService;
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
@RequestMapping("/livro")
@Validated
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class LivroController {

    private final LivroService livroService;

    @GetMapping
    @ApiOperation("Realizar a busca dos livros existentes na biblioteca")
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
    public ResponseEntity<Page<LivroDto>> getLivros(Pageable pageable,
                                                    @RequestParam(value = "disponivel", required = false)
                                                            boolean disponiveis) {
        return ResponseEntity.ok(livroService.findAll(pageable, disponiveis));
    }

    @GetMapping("/{id}")
    @ApiOperation("Realizar a busca de um livro por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "O livro foi localizado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida, valide os parâmetros de entrada", response = ExceptionResponse.class),
            @ApiResponse(code = 404, message = "Livro não encontrado, válide o id informado", response = ExceptionResponse.class)
    })
    public ResponseEntity<LivroDto> getLivro(@PathVariable("id") long id) {
        return ResponseEntity.ok(livroService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Remover um livro por id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "A remoCão foi realizada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida, valide os parâmetros de entrada", response = ExceptionResponse.class),
            @ApiResponse(code = 404, message = "Livro não localizado", response = ExceptionResponse.class)
    })
    public ResponseEntity<Void> deleteLivro(@PathVariable("id") long id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ApiOperation("Realizar o cadastro de um livro")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "O cadastro do livro foi criado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida, valide os parâmetros de entrada", response = ExceptionResponse.class)
    })
    public ResponseEntity<LivroDto> createLivro(@Valid @RequestBody LivroDto livroDto) {
        var livro = livroService.create(livroDto);

        var uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/livro/{id}")
                .buildAndExpand(livro.getId())
                .toUri();

        return ResponseEntity.created(uri).body(livro);
    }

    @PutMapping("/{id}")
    @ApiOperation("Realizar a atualização de um livro")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "O cadastro do livro foi atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida, valide os parâmetros de entrada", response = ExceptionResponse.class)
    })
    public ResponseEntity<LivroDto> updateLivro(@PathVariable("id") long id, @Valid @RequestBody LivroDto livroDto) {
        var livro = livroService.update(id, livroDto);

        var uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/livro/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).body(livro);
    }
}
