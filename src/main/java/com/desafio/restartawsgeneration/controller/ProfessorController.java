package com.desafio.restartawsgeneration.controller;

import com.desafio.restartawsgeneration.entity.Professor;
import com.desafio.restartawsgeneration.exception.ProfessorNotFoundException;
import com.desafio.restartawsgeneration.service.ProfessorService;
import org.jetbrains.annotations.Contract;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfessorController {
    
    private final ProfessorService professorService;

    @Contract(pure = true)
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping("/criar-professor")
    public ResponseEntity<Professor> create(@RequestBody Professor professor) {
        Professor professorCriado = professorService.createProfessor(professor);
        return ResponseEntity.ok(professorCriado);
    }

    @PostMapping("/criar-professores")
    public ResponseEntity<List<Professor>> create(@RequestBody List<Professor> professores) {
        List<Professor> professoresCriados = professorService.createProfessores(professores);
        return ResponseEntity.ok(professoresCriados);
    }

    @GetMapping("/listar-professores")
    public ResponseEntity<List<Professor>> getAll() {
        List<Professor> professores = professorService.getAllProfessores();
        return ResponseEntity.ok(professores);
    }

    @GetMapping("/buscar-professor/{id}")
    public ResponseEntity<Professor> getById(@PathVariable Long id) {
        try {
            Professor professor = professorService.getProfessorById(id);
            return ResponseEntity.ok(professor);
        } catch (ProfessorNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/atualizar-professor")
    public ResponseEntity<Professor> update(@RequestBody Professor professor) {
        try {
            Professor professorAtualizado = professorService.updateProfessor(professor);
            return ResponseEntity.ok(professorAtualizado);
        } catch (ProfessorNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar-professor/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            professorService.deleteProfessorById(id);
            return ResponseEntity.noContent().build();
        } catch (ProfessorNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar-todos-professores")
    public ResponseEntity<Void> deleteAll() {
        professorService.deleteAllProfessores();
        return ResponseEntity.noContent().build();
    }
}
