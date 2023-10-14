package com.desafio.restartawsgeneration.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;

@Data
@Table(
        name = "tb_matricula",
        schema = "db_escola"
)
@Entity
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
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
}