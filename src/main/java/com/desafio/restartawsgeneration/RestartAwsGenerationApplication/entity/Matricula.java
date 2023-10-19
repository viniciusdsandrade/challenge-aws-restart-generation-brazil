package com.desafio.restartawsgeneration.RestartAwsGenerationApplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import java.time.LocalDate;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "Matricula")
@Table(
        name = "tb_matricula",
        schema = "db_escola"
)
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String nome_aluno;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate dataNascimento;

    @Column(nullable = false, columnDefinition = "DECIMAL(4,2)")
    private double notaDoPrimeiroSemestre;

    @Column(nullable = false, columnDefinition = "DECIMAL(4,2)")
    private double notaDoSegundoSemestre;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String nome_professor;

    @Column(nullable = false, columnDefinition = "INT UNSIGNED")
    private int numero;

    @Override
    public String toString() {
        return "{\n" +
                "    \"id\" : " + id + ",\n" +
                "    \"nome_aluno\" :\" " + nome_aluno + "\",\n" +
                "    \"dataNascimento\" :\" " + dataNascimento + "\",\n" +
                "    \"notaDoPrimeiroSemestre\" : " + notaDoPrimeiroSemestre + ",\n" +
                "    \"notaDoSegundoSemestre\" : " + notaDoSegundoSemestre + ",\n" +
                "    \"nome_professor\" :\" " + nome_professor + "\",\n" +
                "    \"numero\" : " + numero + "\n" +
                "}";
    }
}