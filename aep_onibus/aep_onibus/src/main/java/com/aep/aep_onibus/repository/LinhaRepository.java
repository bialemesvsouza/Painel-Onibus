package com.aep.aep_onibus.repository;


import com.aep.aep_onibus.model.Linha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinhaRepository extends JpaRepository<Linha, Integer> {
    Linha findByCodigo(String codigo);
}
