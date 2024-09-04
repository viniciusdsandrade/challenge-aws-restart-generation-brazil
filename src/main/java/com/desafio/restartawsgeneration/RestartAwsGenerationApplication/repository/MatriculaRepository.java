package com.desafio.restartawsgeneration.RestartAwsGenerationApplication.repository;

import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("matriculaRepository")
public interface MatriculaRepository extends JpaRepository<Aluno, Long> {
}
