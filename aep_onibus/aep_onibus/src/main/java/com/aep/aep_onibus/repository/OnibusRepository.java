package com.aep.aep_onibus.repository;

import com.aep.aep_onibus.model.Onibus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnibusRepository extends JpaRepository<Onibus, Integer> {
    Onibus findByCodigo(String codigo);
}
