package com.aep.aep_onibus.service;

import com.aep.aep_onibus.model.Parada;
import com.aep.aep_onibus.repository.ParadaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParadaService {

    private final ParadaRepository repository;

    public ParadaService(ParadaRepository repository) {
        this.repository = repository;
    }

    public List<Parada> findAll() {
        return repository.findAll();
    }

    public Parada findByNome(String nome) {
        return repository.findByCodigo(nome);
    }

    public Parada save(Parada parada) {
        return repository.save(parada);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}

