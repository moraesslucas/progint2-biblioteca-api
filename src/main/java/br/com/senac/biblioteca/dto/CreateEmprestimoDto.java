package br.com.senac.biblioteca.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CreateEmprestimoDto {

    @NotNull(message = "O campo idLivro deve ser informado")
    @Min(value = 1, message = "O campo idLivro deve ser válido")
    private long idLivro;

    @NotNull(message = "O campo idCliente deve ser informado")
    @Min(value = 1, message = "O campo idCliente deve ser válido")
    private long idCliente;

    @NotNull(message = "O campo dataEmprestimo deve ser informado")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataEmprestimo;

}
