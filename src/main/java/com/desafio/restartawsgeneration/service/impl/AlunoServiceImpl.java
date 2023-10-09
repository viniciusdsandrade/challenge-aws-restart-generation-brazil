package com.desafio.restartawsgeneration.service.impl;

import com.desafio.restartawsgeneration.entity.Aluno;
import com.desafio.restartawsgeneration.exception.AlunoNotFoundException;
import com.desafio.restartawsgeneration.repository.AlunoRepository;
import com.desafio.restartawsgeneration.service.AlunoService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository alunoRepository;

    @Contract(pure = true)
    public AlunoServiceImpl(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Override
    public Aluno createAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @Override
    public List<Aluno> createAlunos(@NotNull List<Aluno> alunos) {
        return alunos.stream()
                .map(this::createAluno)
                .collect(Collectors.toList());
    }

    @Override
    public Aluno getAlunoById(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNotFoundException("Aluno não encontrado com o ID: " + id));
    }

    @Override
    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }

    @Override
    public Aluno updateAluno(@NotNull Aluno aluno) {
        if (aluno.getId() == null) {
            throw new IllegalArgumentException("ID do aluno não pode ser nulo");
        }

        Aluno existingAluno = getAlunoById(aluno.getId());

        existingAluno.setNome(aluno.getNome());
        existingAluno.setDataNascimento(aluno.getDataNascimento());
        existingAluno.setNotaDoPrimeiroSemestre(aluno.getNotaDoPrimeiroSemestre());
        existingAluno.setNotaDoSegundoSemestre(aluno.getNotaDoSegundoSemestre());

        return alunoRepository.saveAndFlush(existingAluno);
    }

    @Override
    public void deleteAlunoById(Long id) {
        try{
            Aluno existingAluno = getAlunoById(id);
            alunoRepository.delete(existingAluno);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAllAlunos() {
        try{
            alunoRepository.deleteAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
