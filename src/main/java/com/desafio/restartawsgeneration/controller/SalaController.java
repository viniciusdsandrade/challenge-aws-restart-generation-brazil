package com.desafio.restartawsgeneration.controller;

import com.desafio.restartawsgeneration.entity.Sala;
import com.desafio.restartawsgeneration.exception.SalaNotFoundException;
import com.desafio.restartawsgeneration.service.SalaService;
import org.jetbrains.annotations.Contract;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sala")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SalaController {

    private final SalaService salaService;

    @Contract(pure = true)
    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @PostMapping("/criar-sala")
    public ResponseEntity<Sala> create(@RequestBody Sala sala) {
        Sala salaCriada = salaService.createSala(sala);
        return ResponseEntity.ok(salaCriada);
    }
    
    @PostMapping("/criar-salas")
    public ResponseEntity<List<Sala>> create(@RequestBody List<Sala> salas) {
        List<Sala> salasCriadas = salaService.createSalas(salas);
        return ResponseEntity.ok(salasCriadas);
    }

    @GetMapping("/listar-salas")
    public ResponseEntity<List<Sala>> getAll() {
        List<Sala> salas = salaService.getAllSalas();
        return ResponseEntity.ok(salas);
    }

    @GetMapping("/buscar-sala/{id}")
    public ResponseEntity<Sala> getById(@PathVariable Long id) throws SalaNotFoundException{
        try{
            Sala sala = salaService.getSalaById(id);
            return ResponseEntity.ok(sala);
        }catch (SalaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/atualizar-sala")
    public ResponseEntity<Sala> update(@RequestBody Sala sala) {
        try{
            Sala salaAtualizada = salaService.updateSala(sala);
            return ResponseEntity.ok(salaAtualizada);
        } catch (SalaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar-sala/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try{
            salaService.deleteSalaById(id);
            return ResponseEntity.noContent().build();
        } catch (SalaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar-salas")
    public ResponseEntity<Void> deleteAll() {
        salaService.deleteAllSalas();
        return ResponseEntity.noContent().build();
    }
}
