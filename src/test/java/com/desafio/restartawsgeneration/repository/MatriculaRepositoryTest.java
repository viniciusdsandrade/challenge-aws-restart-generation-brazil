package com.desafio.restartawsgeneration.repository;

import com.desafio.restartawsgeneration.entity.Matricula;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MatriculaRepositoryTest {

    @Autowired
    private MatriculaRepository repository;

    @Test
    @DisplayName("Test do JUnit para salvar o aluno")
    void givenAlunoDTO_whenSave_thenGetOk() {

        Matricula matricula = Matricula.builder()
                .nome_aluno("João")
                .dataNascimento(LocalDate.of(2000, 1, 1))
                .notaDoPrimeiroSemestre(10.00)
                .notaDoSegundoSemestre(10.00)
                .nome_professor("Maria")
                .numero(1)
                .build();

        Matricula matriculaSalva = repository.save(matricula);
        System.out.println(matriculaSalva);

        Assertions.assertThat(matriculaSalva.getId()).isNotNull();
        Assertions.assertThat(matriculaSalva.getId()).isGreaterThan(0L);
    }

    @Test
    @DisplayName("Teste do JUnit para buscar um aluno por ID")
    void givenAlunoId_whenFindById_thenGetAluno() {
        Matricula matricula = Matricula.builder()
                .nome_aluno("Maria")
                .dataNascimento(LocalDate.of(1995, 5, 15))
                .notaDoPrimeiroSemestre(9.5)
                .notaDoSegundoSemestre(9.8)
                .nome_professor("Carlos")
                .numero(2)
                .build();

        Matricula matriculaSalva = repository.save(matricula);
        Long id = matriculaSalva.getId();

        Matricula alunoRecuperado = repository.findById(id).orElse(null);

        Assertions.assertThat(alunoRecuperado).isNotNull();
        Assertions.assertThat(alunoRecuperado.getId()).isEqualTo(id);
        Assertions.assertThat(alunoRecuperado.getNome_aluno()).isEqualTo("Maria");
    }

    @Test
    @DisplayName("Teste do JUnit para atualizar um aluno")
    void givenAlunoId_whenUpdate_thenGetUpdatedAluno() {
        Matricula matricula = Matricula.builder()
                .nome_aluno("Lucas")
                .dataNascimento(LocalDate.of(1998, 3, 10))
                .notaDoPrimeiroSemestre(8.7)
                .notaDoSegundoSemestre(8.9)
                .nome_professor("Ana")
                .numero(3)
                .build();

        Matricula matriculaSalva = repository.save(matricula);
        Long id = matriculaSalva.getId();

        Matricula alunoAtualizado = Matricula.builder()
                .id(id)
                .nome_aluno("Lucas Silva")
                .dataNascimento(LocalDate.of(1998, 3, 10))
                .notaDoPrimeiroSemestre(9.0)
                .notaDoSegundoSemestre(9.2)
                .nome_professor("Ana Maria")
                .numero(4)
                .build();

        Matricula alunoAtualizadoSalvo = repository.save(alunoAtualizado);

        Assertions.assertThat(alunoAtualizadoSalvo).isNotNull();
        Assertions.assertThat(alunoAtualizadoSalvo.getId()).isEqualTo(id);
        Assertions.assertThat(alunoAtualizadoSalvo.getNome_aluno()).isEqualTo("Lucas Silva");
        Assertions.assertThat(alunoAtualizadoSalvo.getNotaDoPrimeiroSemestre()).isEqualTo(9.0);
        Assertions.assertThat(alunoAtualizadoSalvo.getNome_professor()).isEqualTo("Ana Maria");
    }

    @Test
    @DisplayName("Teste do JUnit para excluir um aluno")
    void givenAlunoId_whenDelete_thenAlunoNotExists() {
        Matricula matricula = Matricula.builder()
                .nome_aluno("Mariana")
                .dataNascimento(LocalDate.of(1992, 8, 20))
                .notaDoPrimeiroSemestre(8.0)
                .notaDoSegundoSemestre(8.3)
                .nome_professor("Paulo")
                .numero(5)
                .build();

        Matricula matriculaSalva = repository.save(matricula);
        Long id = matriculaSalva.getId();

        repository.delete(matriculaSalva);

        Matricula alunoExcluido = repository.findById(id).orElse(null);

        Assertions.assertThat(alunoExcluido).isNull();
    }

    @Test
    @DisplayName("Teste do JUnit para listar todos os alunos")
    void givenAlunos_whenFindAll_thenGetAlunos() {
        Matricula aluno1 = Matricula.builder()
                .nome_aluno("Fernanda")
                .dataNascimento(LocalDate.of(1990, 7, 5))
                .notaDoPrimeiroSemestre(7.5)
                .notaDoSegundoSemestre(7.8)
                .nome_professor("Ricardo")
                .numero(6)
                .build();

        Matricula aluno2 = Matricula.builder()
                .nome_aluno("Paulo")
                .dataNascimento(LocalDate.of(1991, 4, 12))
                .notaDoPrimeiroSemestre(8.2)
                .notaDoSegundoSemestre(8.5)
                .nome_professor("Sandra")
                .numero(7)
                .build();

        repository.save(aluno1);
        repository.save(aluno2);

        List<Matricula> alunos = repository.findAll();

        Assertions.assertThat(alunos).isNotNull();
        Assertions.assertThat(alunos.size()).isGreaterThanOrEqualTo(2);
    }

    @Test
    @DisplayName("Teste do JUnit para buscar alunos por nome")
    void givenAlunoNome_whenFindByNome_thenGetAlunos() {
        Matricula aluno1 = Matricula.builder()
                .nome_aluno("Laura")
                .dataNascimento(LocalDate.of(1993, 11, 30))
                .notaDoPrimeiroSemestre(7.0)
                .notaDoSegundoSemestre(7.2)
                .nome_professor("Luiza")
                .numero(8)
                .build();

        Matricula aluno2 = Matricula.builder()
                .nome_aluno("Laura")
                .dataNascimento(LocalDate.of(1994, 2, 15))
                .notaDoPrimeiroSemestre(8.5)
                .notaDoSegundoSemestre(8.8)
                .nome_professor("Carlos")
                .numero(9)
                .build();

        repository.save(aluno1);
        repository.save(aluno2);

        List<Matricula> alunos = repository.findByNomeAluno("Laura");

        Assertions.assertThat(alunos).isNotNull();
        Assertions.assertThat(alunos.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Teste do JUnit para validação de dados obrigatórios")
    void givenAlunoComDadosFaltando_whenSave_thenValidationFails() {
        Matricula aluno = Matricula.builder()
                .dataNascimento(LocalDate.of(1985, 3, 25))
                .notaDoPrimeiroSemestre(7.7)
                .notaDoSegundoSemestre(8.0)
                .numero(10)
                .build();

        Assertions.assertThatThrownBy(() -> repository.save(aluno))
                .isInstanceOf(Exception.class);
    }
}