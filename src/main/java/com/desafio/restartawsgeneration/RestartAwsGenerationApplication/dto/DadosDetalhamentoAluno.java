package com.desafio.restartawsgeneration.RestartAwsGenerationApplication.dto;

import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.entity.Aluno;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Schema(description = "Dados detalhamento aluno")
public record DadosDetalhamentoAluno(
        @Schema(description = "Nome do aluno")
        Long id,

        @Schema(description = "Nome do aluno")
        String nome,

        @JsonFormat(pattern = "dd/MM/yyyy", shape = STRING, locale = "pt-BR", timezone = "Brazil/East")
        @Schema(description = "Data de nascimento do aluno.")
        LocalDate dataNascimento,

        @Schema(description = "Idade do aluno em anos, meses e dias.")
        String idade,

        @Schema(description = "Nota do primeiro semestre")
        String notaDoPrimeiroSemestre,

        @Schema(description = "Nota do segundo semestre")
        String notaDoSegundoSemestre,

        @Schema(description = "Nome do professor")
        String nomeProfessor
) {

    public DadosDetalhamentoAluno(Aluno aluno) {
        this(
                aluno.getId(),
                aluno.getNome(),
                aluno.getDataNascimento(),
                calculateIdade(aluno.getDataNascimento(), LocalDate.now()),
                formatarNota(aluno.getNotaDoPrimeiroSemestre()),
                formatarNota(aluno.getNotaDoSegundoSemestre()),
                aluno.getNomeProfessor()
        );
    }

    private static String formatarNota(double nota) {
        DecimalFormat df = new DecimalFormat("##.##");
        return df.format(nota);
    }

    private static String calculateIdade(LocalDate dataNascimento, LocalDate dataAtual) {
        Period periodo = Period.between(dataNascimento, dataAtual);
        return String.format("%d anos, %d meses e %d dias",
                periodo.getYears(),
                periodo.getMonths(),
                periodo.getDays());
    }
}
