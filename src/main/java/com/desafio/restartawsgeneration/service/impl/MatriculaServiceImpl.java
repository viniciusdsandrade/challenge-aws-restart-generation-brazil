package com.desafio.restartawsgeneration.service.impl;

import com.desafio.restartawsgeneration.entity.Aluno;
import com.desafio.restartawsgeneration.entity.Matricula;
import com.desafio.restartawsgeneration.entity.Professor;
import com.desafio.restartawsgeneration.entity.Sala;
import com.desafio.restartawsgeneration.repository.MatriculaRepository;
import com.desafio.restartawsgeneration.service.MatriculaService;
import org.jetbrains.annotations.Contract;
import org.springframework.stereotype.Service;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository matriculaRepository;

    @Contract(pure = true)
    public MatriculaServiceImpl(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    @Override
    public Matricula createMatricula(Matricula matricula) {
        // Extrair dados do JSON para criar as instâncias das classes Aluno, Professor e Sala
        Aluno aluno = matricula.getAluno();
        Professor professor = matricula.getProfessor();
        Sala sala = matricula.getSala();

        // Criar uma instância de  com as instâncias de Aluno, Professor e Sala
        Matricula novaMaTricula = new Matricula(aluno, professor, sala);

        // Salvar a matrícula no repositório
        return matriculaRepository.save(novaMaTricula);
        
    }
}