package com.aep.aep_onibus.service;

import com.aep.aep_onibus.model.Onibus;
import com.aep.aep_onibus.repository.OnibusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnibusService {

    private final OnibusRepository repository;

    public OnibusService(OnibusRepository repository) {
        this.repository = repository;
    }

    public List<Onibus> findAll() {
        return repository.findAll();
    }

    public Onibus findByCodigo(String codigo) {
        return repository.findByCodigo(codigo);
    }

    public Onibus save(Onibus onibus) {
        return repository.save(onibus);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
