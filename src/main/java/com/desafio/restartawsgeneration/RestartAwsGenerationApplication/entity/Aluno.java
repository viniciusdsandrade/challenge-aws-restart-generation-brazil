package com.desafio.restartawsgeneration.RestartAwsGenerationApplication.entity;

import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.dto.DadosAtualizacaoAluno;
import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.dto.DadosCadastroAluno;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;
import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_WRITE;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.LocalDateTime.now;
import static java.util.Optional.ofNullable;
import static java.util.Optional.of;
import static lombok.AccessLevel.NONE;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity(name = "Aluno")
@Table(name = "tb_aluno", schema = "db_escola")
@Schema(description = "Entidade Aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Schema(description = "Nome do aluno")
    private String nome;

    @Schema(description = "Data de nascimento do aluno.")
    private LocalDate dataNascimento;

    @Schema(description = "Nota do primeiro semestre")
    @Column(columnDefinition = "FLOAT DEFAULT 0.0", nullable = false)
    private float notaDoPrimeiroSemestre;

    @Schema(description = "Nota do segundo semestre")
    @Column(columnDefinition = "FLOAT DEFAULT 0.0", nullable = false)
    private float notaDoSegundoSemestre;

    @Schema(description = "Nome do professor")
    private String nomeProfessor;

    @Schema(description = "Número da sala")
    private int numeroSala;

    @CreationTimestamp
    @Setter(NONE)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false)
    @Schema(description = "Data e hora de criação do registro.", accessMode = READ_ONLY)
    private LocalDateTime dataCreated;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @Schema(description = "Data e hora da última atualização do registro.", accessMode = READ_WRITE)
    private LocalDateTime lastUpdated;

    public Aluno(@Valid DadosCadastroAluno dadosCadastroAluno) {
        this.setNome(dadosCadastroAluno.nome());
        this.setDataNascimento(dadosCadastroAluno.dataNascimento());
        this.setNotaDoPrimeiroSemestre(dadosCadastroAluno.notaDoPrimeiroSemestre());
        this.setNotaDoSegundoSemestre(dadosCadastroAluno.notaDoSegundoSemestre());
        this.setNomeProfessor(dadosCadastroAluno.nomeProfessor());
        this.setNumeroSala(dadosCadastroAluno.numeroSala());
        this.setLastUpdated(now());
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoAluno dadosCadastroAluno) {
        ofNullable(dadosCadastroAluno.nome()).ifPresent(this::setNome);
        ofNullable(dadosCadastroAluno.dataNascimento()).ifPresent(this::setDataNascimento);
        of(dadosCadastroAluno.notaDoPrimeiroSemestre()).ifPresent(this::setNotaDoPrimeiroSemestre);
        of(dadosCadastroAluno.notaDoSegundoSemestre()).ifPresent(this::setNotaDoSegundoSemestre);
        ofNullable(dadosCadastroAluno.nomeProfessor()).ifPresent(this::setNomeProfessor);
        of(dadosCadastroAluno.numeroSala()).ifPresent(this::setNumeroSala);
        this.setLastUpdated(now());
    }

    @Override
    public String toString() {
        return "{\n" +
                "    \"id\" : " + id + ",\n" +
                "    \"nome_aluno\" :\" " + nome + "\",\n" +
                "    \"dataNascimento\" :\" " + dataNascimento + "\",\n" +
                "    \"notaDoPrimeiroSemestre\" : " + notaDoPrimeiroSemestre + ",\n" +
                "    \"notaDoSegundoSemestre\" : " + notaDoSegundoSemestre + ",\n" +
                "    \"nome_professor\" :\" " + nomeProfessor + "\",\n" +
                "    \"numero\" : " + numeroSala + "\n" +
                "}";
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Aluno aluno = (Aluno) o;
        return getId() != null && Objects.equals(getId(), aluno.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}