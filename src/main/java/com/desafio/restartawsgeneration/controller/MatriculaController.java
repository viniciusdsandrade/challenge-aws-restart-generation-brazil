package com.desafio.restartawsgeneration.controller;

import com.desafio.restartawsgeneration.dto.AlunoDTO;
import com.desafio.restartawsgeneration.entity.Aluno;
import com.desafio.restartawsgeneration.entity.Matricula;
import com.desafio.restartawsgeneration.entity.Professor;
import com.desafio.restartawsgeneration.entity.Sala;
import com.desafio.restartawsgeneration.service.MatriculaService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matricula")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MatriculaController {

    private final MatriculaService matriculaService;

    @Contract(pure = true)
    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }
    
    @PostMapping("/create")
    public ResponseEntity<Matricula> createMatricula(@RequestBody Matricula matricula) {
        return ResponseEntity.ok(matriculaService.createMatricula(matricula));
    }
    
    


}