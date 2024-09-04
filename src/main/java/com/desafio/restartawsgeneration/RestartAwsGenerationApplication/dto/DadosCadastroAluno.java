package com.desafio.restartawsgeneration.RestartAwsGenerationApplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Schema(name = "DadosCadastroAluno")
public record DadosCadastroAluno(
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 80, message = "O nome deve ter entre 3 e 80 caracteres")
        @Schema(description = "Nome do aluno")
        String nome,

        @NotNull(message = "Data de nascimento é obrigatória")
        @JsonFormat(pattern = "dd/MM/yyyy", shape = STRING, locale = "pt-BR", timezone = "Brazil/East")
        @Schema(description = "Data de nascimento do motorista.")
        LocalDate dataNascimento,

        @NotNull(message = "Nota do primeiro semestre é obrigatória")
        @DecimalMin(value = "0.0", message = "A nota do primeiro semestre deve ser maior ou igual a 0.0")
        @DecimalMax(value = "10.0", message = "A nota do primeiro semestre deve ser menor ou igual a 10.0")
        @Schema(description = "Nota do primeiro semestre")
        float notaDoPrimeiroSemestre,

        @NotNull(message = "Nota do segundo semestre é obrigatória")
        @DecimalMin(value = "0.0", message = "A nota do segundo semestre deve ser maior ou igual a 0.0")
        @DecimalMax(value = "10.0", message = "A nota do segundo semestre deve ser menor ou igual a 10.0")
        @Schema(description = "Nota do segundo semestre")
        float notaDoSegundoSemestre,

        @NotBlank(message = "Nome do professor é obrigatório")
        @Size(min = 3, max = 80, message = "O nome do professor deve ter entre 3 e 80 caracteres")
        @Schema(description = "Nome do professor")
        String nomeProfessor,

        @NotNull(message = "Número da sala é obrigatório")
        @Schema(description = "Número da sala")
        int numeroSala
) {
}
