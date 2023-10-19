package com.desafio.restartawsgeneration.RestartAwsGenerationApplication.service;

import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.entity.Matricula;

import java.util.List;

public interface MatriculaService {

    Matricula createMatricula(Matricula aluno);

    List<Matricula> createMatriculas(List<Matricula> alunos);

    Matricula getMatriculaById(Long id);

    List<Matricula> getAllMatriculas();

    Matricula updateMatricula(Matricula aluno);

    void deleteMatriculaById(Long id);

    void deleteAllMatriculas();
}
