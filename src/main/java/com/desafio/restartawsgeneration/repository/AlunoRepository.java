package com.desafio.restartawsgeneration.repository;

import com.desafio.restartawsgeneration.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository  extends JpaRepository<Aluno, Long> {
}
