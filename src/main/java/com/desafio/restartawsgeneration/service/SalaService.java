package com.desafio.restartawsgeneration.service;

import com.desafio.restartawsgeneration.entity.Sala;

import java.util.List;

public interface SalaService {

    Sala createSala(Sala sala);

    List<Sala> createSalas(List<Sala> salas);

    Sala getSalaById(Long id);

    List<Sala> getAllSalas();

    Sala updateSala(Sala sala);

    void deleteSalaById(Long id);

    void deleteAllSalas();
}