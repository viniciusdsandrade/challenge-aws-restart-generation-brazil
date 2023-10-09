package com.desafio.restartawsgeneration.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "matricula")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "aluno_id",
            columnDefinition = "BIGINT UNSIGNED"
    )
    private Aluno aluno;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "professor_id",
            columnDefinition = "BIGINT UNSIGNED"
    )
    private Professor professor;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "sala_id",
            columnDefinition = "BIGINT UNSIGNED"
    )
    private Sala sala;

    public Matricula(Aluno aluno, Professor professor, Sala sala) {
        this.aluno = aluno;
        this.professor = professor;
        this.sala = sala;
    }
}