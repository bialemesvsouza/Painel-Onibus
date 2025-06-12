package com.aep.aep_onibus.service;

import com.aep.aep_onibus.model.Painel;
import com.aep.aep_onibus.repository.PainelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PainelService {

    private final PainelRepository repository;

    public PainelService(PainelRepository repository) {
        this.repository = repository;
    }

    public List<Painel> findAll() {
        return repository.findAll();
    }

    public Painel findByLocalizacao(String localizacao) {
        return repository.findByLocalizacao(localizacao);
    }

    public Painel save(Painel painel) {
        return repository.save(painel);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}

