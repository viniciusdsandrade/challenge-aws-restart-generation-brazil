package com.desafio.restartawsgeneration.controller;

import com.desafio.restartawsgeneration.entity.Aluno;
import com.desafio.restartawsgeneration.exception.AlunoNotFoundException;
import com.desafio.restartawsgeneration.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("/criar-aluno")
    public ResponseEntity<Aluno> create(@RequestBody Aluno aluno) {
        Aluno alunoCriado = alunoService.createAluno(aluno);
        return ResponseEntity.ok(alunoCriado);
    }

    @PostMapping("/criar-alunos")
    public ResponseEntity<List<Aluno>> createAlunos(@RequestBody List<Aluno> alunos) {
        List<Aluno> alunosCriados = alunoService.createAlunos(alunos);
        return ResponseEntity.ok(alunosCriados);
    }

    @GetMapping("/listar-alunos")
    public ResponseEntity<List<Aluno>> getAll() {
        List<Aluno> alunos = alunoService.getAllAlunos();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/buscar-aluno/{id}")
    public ResponseEntity<Aluno> getById(@PathVariable Long id) throws AlunoNotFoundException {
        try{
            Aluno aluno = alunoService.getAlunoById(id);
            return ResponseEntity.ok(aluno);
        } catch (AlunoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/atualizar-aluno")
    public ResponseEntity<Aluno> update(@RequestBody Aluno aluno) {
        Aluno alunoAtualizado = alunoService.updateAluno(aluno);
        return ResponseEntity.ok(alunoAtualizado);
    }

    @DeleteMapping("/deletar-aluno/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try{
            alunoService.deleteAlunoById(id);
            return ResponseEntity.noContent().build();
        } catch (AlunoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar-todos-alunos")
    public ResponseEntity<Void> deleteAll() {
        alunoService.deleteAllAlunos();
        return ResponseEntity.noContent().build();
    }
}