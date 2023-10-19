package com.desafio.restartawsgeneration.RestartAwsGenerationApplication.controller;


import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.entity.Matricula;
import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.exception.MatriculaNotFoundException;
import com.desafio.restartawsgeneration.RestartAwsGenerationApplication.service.MatriculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matricula")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MatriculaController {

    private final MatriculaService matriculaService;


    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping("/criar-matricula")
    public ResponseEntity<Matricula> createMatricula(@RequestBody Matricula matricula) {
        Matricula matriculaCriada = matriculaService.createMatricula(matricula);
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaCriada);
    }

    @PostMapping("/criar-matriculas")
    public ResponseEntity<List<Matricula>> createMatriculas(@RequestBody List<Matricula> matriculas) {
        List<Matricula> matriculasCriadas = matriculaService.createMatriculas(matriculas);
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculasCriadas);
    }

    @GetMapping("/listar-matriculas")
    public ResponseEntity<List<Matricula>> getAllMatriculas() {
        List<Matricula> matriculas = matriculaService.getAllMatriculas();
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/buscar-matricula/{id}")
    public ResponseEntity<Matricula> getMatriculaById(@PathVariable Long id) {
        try {
            Matricula matricula = matriculaService.getMatriculaById(id);
            return ResponseEntity.ok(matricula);
        } catch (MatriculaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/atualizar-matricula")
    public ResponseEntity<Matricula> updateMatricula(@RequestBody Matricula matricula) {
        Matricula matriculaAtualizada = matriculaService.updateMatricula(matricula);
        return ResponseEntity.ok(matriculaAtualizada);
    }

    @DeleteMapping("/deletar-matricula/{id}")
    public ResponseEntity<Void> deleteMatricula(@PathVariable Long id) {
        try {
            matriculaService.deleteMatriculaById(id);
            return ResponseEntity.noContent().build();
        } catch (MatriculaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar-todas-matriculas")
    public ResponseEntity<Void> deleteAllMatriculas() {
        matriculaService.deleteAllMatriculas();
        return ResponseEntity.noContent().build();
    }
}
