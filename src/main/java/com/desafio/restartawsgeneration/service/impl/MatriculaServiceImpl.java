package com.desafio.restartawsgeneration.service.impl;

import com.desafio.restartawsgeneration.entity.Matricula;
import com.desafio.restartawsgeneration.exception.MatriculaNotFoundException;
import com.desafio.restartawsgeneration.repository.MatriculaRepository;
import com.desafio.restartawsgeneration.service.MatriculaService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository matriculaRepository;

    @Autowired
    public MatriculaServiceImpl(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    @Override
    public Matricula createMatricula(Matricula aluno) {
        return matriculaRepository.save(aluno);
    }

    @Override
    public List<Matricula> createMatriculas(@NotNull List<Matricula> alunos) {
        return alunos.stream()
                .map(this::createMatricula)
                .collect(Collectors.toList());
    }

    @Override
    public Matricula getMatriculaById(Long id) {
        Optional<Matricula> matriculaOptional = matriculaRepository.findById(id);
        return matriculaOptional.orElseThrow(() -> new MatriculaNotFoundException("Matricula not found!"));
    }

    @Override
    public List<Matricula> getAllMatriculas() {
        return matriculaRepository.findAll();
    }

    @Override
    public Matricula updateMatricula(@NotNull Matricula aluno) {
        if (aluno.getId() == null) {
            throw new IllegalArgumentException("Aluno ID cannot be null");
        }

        Matricula existingAluno = getMatriculaById(aluno.getId());
        copyProperties(aluno, existingAluno);
        return matriculaRepository.save(existingAluno);
    }

    @Override
    public void deleteMatriculaById(Long id) {
        try {
            Matricula existingAluno = getMatriculaById(id);
            matriculaRepository.delete(existingAluno);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAllMatriculas() {
        try {
            matriculaRepository.deleteAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void copyProperties(@NotNull Matricula source, @NotNull Matricula target) {
        target.setNome_aluno(source.getNome_aluno());
        target.setDataNascimento(source.getDataNascimento());
        target.setNotaDoPrimeiroSemestre(source.getNotaDoPrimeiroSemestre());
        target.setNotaDoSegundoSemestre(source.getNotaDoSegundoSemestre());
        target.setNome_professor(source.getNome_professor());
        target.setNumero(source.getNumero());
    }
}
