package com.desafio.restartawsgeneration.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Sala")
@Table(name = "tb_sala")
public class Sala {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @EqualsAndHashCode.Include
    private Long id;
    
    @Column(nullable = false,
            columnDefinition = "INT UNSIGNED")
    private Integer numeroSala;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "sala"
    )
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private List<Matricula> matriculas;
}
