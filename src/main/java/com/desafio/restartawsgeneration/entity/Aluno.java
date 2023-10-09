package com.desafio.restartawsgeneration.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Aluno")
@Table(
        name = "tb_aluno",
        schema = "db_escola"
)
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false,
            columnDefinition = "VARCHAR(255)")
    private String nome;

    @Column(nullable = false,
            columnDefinition = "DATE")
    private LocalDate dataNascimento;

    @Column(nullable = false,
            columnDefinition = "DECIMAL(4,2)")
    private Double notaDoPrimeiroSemestre;

    @Column(nullable = false,
            columnDefinition = "DECIMAL(4,2)")
    private Double notaDoSegundoSemestre;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "aluno"
    )
    private Set<Matricula> matriculas = new HashSet<>();
}