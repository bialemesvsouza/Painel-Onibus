package com.aep.aep_onibus.repository;

import com.aep.aep_onibus.model.Parada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParadaRepository extends JpaRepository<Parada, Integer> {
    Parada findByCodigo(String Codigo);
}

