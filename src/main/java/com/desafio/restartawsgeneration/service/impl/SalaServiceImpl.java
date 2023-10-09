package com.desafio.restartawsgeneration.service.impl;

import com.desafio.restartawsgeneration.entity.Sala;
import com.desafio.restartawsgeneration.exception.SalaNotFoundException;
import com.desafio.restartawsgeneration.repository.SalaRepository;
import com.desafio.restartawsgeneration.service.SalaService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaServiceImpl implements SalaService {

    private final SalaRepository salaRepository;

    @Contract(pure = true)
    public SalaServiceImpl(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    @Override
    public Sala createSala(Sala sala) {
        return salaRepository.save(sala);
    }

    @Override
    public List<Sala> createSalas(@NotNull List<Sala> salas) {
        return salas.stream()
                .map(this::createSala)
                .collect(Collectors.toList());
    }

    @Override
    public Sala getSalaById(Long id) throws SalaNotFoundException {
        return salaRepository.findById(id)
                .orElseThrow(() -> new SalaNotFoundException("Sala não encontrada com o ID: " + id));
    }

    @Override
    public List<Sala> getAllSalas() {
        return salaRepository.findAll();
    }

    @Override
    public Sala updateSala(@NotNull Sala sala) {
        if(sala.getId() == null) {
            throw new IllegalArgumentException("ID da sala não pode ser nulo");
        }
        
        Sala existingSala = getSalaById(sala.getId());
        
        existingSala.setNumeroSala(sala.getNumeroSala());
        
        return salaRepository.saveAndFlush(existingSala);        
    }

    @Override
    public void deleteSalaById(Long id) {
        try{
            Sala existingSala = getSalaById(id);
            salaRepository.delete(existingSala);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAllSalas() {

    }
}
