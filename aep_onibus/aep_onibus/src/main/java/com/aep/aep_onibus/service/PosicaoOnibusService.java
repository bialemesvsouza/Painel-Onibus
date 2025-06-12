package com.aep.aep_onibus.service;


import com.aep.aep_onibus.model.PosicaoOnibus;
import com.aep.aep_onibus.repository.PosicaoOnibusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosicaoOnibusService {

    private final PosicaoOnibusRepository repository;

    public PosicaoOnibusService(PosicaoOnibusRepository repository) {
        this.repository = repository;
    }

    public List<PosicaoOnibus> findAll() {
        return repository.findAll();
    }

    public List<PosicaoOnibus> findByOnibusId(Integer onibusId) {
        return repository.findByOnibusLinhaIdOrderByDataHoraDesc(onibusId);
    }

    public PosicaoOnibus save(PosicaoOnibus posicao) {
        return repository.save(posicao);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
