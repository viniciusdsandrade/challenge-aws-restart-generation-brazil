package com.desafio.restartawsgeneration.service;

import com.desafio.restartawsgeneration.entity.Professor;

import java.util.List;

public interface ProfessorService {

    Professor createProfessor(Professor professor);

    List<Professor> createProfessores(List<Professor> professores);

    Professor getProfessorById(Long id);

    List<Professor> getAllProfessores();

    Professor updateProfessor(Professor professor);

    void deleteProfessorById(Long id);

    void deleteAllProfessores();
}
