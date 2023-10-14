package com.desafio.restartawsgeneration.repository;

import com.desafio.restartawsgeneration.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    
    @Query("SELECT m FROM Matricula m WHERE m.nome_aluno = ?1")
    List<Matricula> findByNomeAluno(String laura);
}
