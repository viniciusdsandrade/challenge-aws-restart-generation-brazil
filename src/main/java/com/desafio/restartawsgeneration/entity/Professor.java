package com.desafio.restartawsgeneration.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Professor")
@Table(
        name = "tb_professor",
        schema = "db_escola"
)
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false,
            columnDefinition = "VARCHAR(255)")
    private String nome;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "professor"
    )
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private List<Matricula> matriculas;
}
