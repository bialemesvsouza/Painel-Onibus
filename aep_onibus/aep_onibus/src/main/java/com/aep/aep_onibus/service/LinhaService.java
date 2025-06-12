package com.aep.aep_onibus.service;


import com.aep.aep_onibus.model.Linha;
import com.aep.aep_onibus.repository.LinhaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinhaService {

    private final LinhaRepository repository;

    public LinhaService(LinhaRepository repository) {
        this.repository = repository;
    }

    public List<Linha> findAll() {
        return repository.findAll();
    }

    public Linha findByNumero(String codigo) {
        return repository.findByCodigo(codigo);
    }

    public Linha save(Linha linha) {
        return repository.save(linha);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
