package com.desafio.restartawsgeneration.service.impl;

import com.desafio.restartawsgeneration.entity.Professor;
import com.desafio.restartawsgeneration.exception.ProfessorNotFoundException;
import com.desafio.restartawsgeneration.repository.ProfessorRepository;
import com.desafio.restartawsgeneration.service.ProfessorService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;

    @Contract(pure = true)
    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public Professor createProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public List<Professor> createProfessores(@NotNull List<Professor> professores) {
        return professores.stream()
                .map(this::createProfessor)
                .collect(Collectors.toList());
    }

    @Override
    public Professor getProfessorById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new ProfessorNotFoundException("Professor não encontrado"));
    }

    @Override
    public List<Professor> getAllProfessores() {
        return professorRepository.findAll();
    }

    @Override
    public Professor updateProfessor(@NotNull Professor professor) {
        if (professor.getId() == null) {
            throw new IllegalArgumentException("ID do professor não pode ser nulo");
        }

        Professor existingProfessor = getProfessorById(professor.getId());

        existingProfessor.setNome(professor.getNome());

        return professorRepository.saveAndFlush(existingProfessor);
    }

    @Override
    public void deleteProfessorById(Long id) {
        try {
            Professor professor = getProfessorById(id);
            professorRepository.delete(professor);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível deletar o professor");
        }
    }
    
    
    @Override
    public void deleteAllProfessores() {
        try {
            professorRepository.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível deletar os professores");
        }
    }
}
