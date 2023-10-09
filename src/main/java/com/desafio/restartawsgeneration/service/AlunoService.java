package com.desafio.restartawsgeneration.service;

import com.desafio.restartawsgeneration.entity.Aluno;

import java.util.List;

public interface AlunoService {

    Aluno createAluno(Aluno aluno);
    
    List<Aluno> createAlunos(List<Aluno> alunos);

    Aluno getAlunoById(Long id);
    
    List<Aluno> getAllAlunos();

    Aluno updateAluno(Aluno aluno);

    void deleteAlunoById(Long id);
    
    void deleteAllAlunos();
}