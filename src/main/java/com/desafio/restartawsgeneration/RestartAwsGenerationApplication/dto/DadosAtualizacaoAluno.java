package com.desafio.restartawsgeneration.RestartAwsGenerationApplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Schema(name = "DadosAtualizacaoAluno")
public record DadosAtualizacaoAluno(

        @NotNull(message = "ID do aluno é obrigatório")
        @Schema(description = "ID do aluno")
        Long id,

        @Size(min = 3, max = 80, message = "O nome deve ter entre 3 e 80 caracteres")
        @Schema(description = "Nome do aluno")
        String nome,

        @Past(message = "A data de nascimento deve ser anterior à data atual")
        @JsonFormat(pattern = "dd/MM/yyyy", shape = STRING, locale = "pt-BR", timezone = "Brazil/East")
        @Schema(description = "Data de nascimento do motorista.")
        LocalDate dataNascimento,

        @DecimalMin(value = "0.0", message = "A nota do primeiro semestre deve ser maior ou igual a 0.0")
        @DecimalMax(value = "10.0", message = "A nota do primeiro semestre deve ser menor ou igual a 10.0")
        @Schema(description = "Nota do primeiro semestre")
        Float notaDoPrimeiroSemestre,

        @DecimalMin(value = "0.0", message = "A nota do segundo semestre deve ser maior ou igual a 0.0")
        @DecimalMax(value = "10.0", message = "A nota do segundo semestre deve ser menor ou igual a 10.0")
        @Schema(description = "Nota do segundo semestre")
        Float notaDoSegundoSemestre,

        @Size(min = 3, max = 80, message = "O nome do professor deve ter entre 3 e 80 caracteres")
        @Schema(description = "Nome do professor")
        String nomeProfessor,

        @Schema(description = "Número da sala")
        @Positive(message = "O número da sala deve ser maior que zero")
        Integer numeroSala
) {
}