package com.aep.aep_onibus.repository;

import com.aep.aep_onibus.model.Painel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PainelRepository extends JpaRepository<Painel, Integer> {
    Painel findByLocalizacao(String localizacao);
}

