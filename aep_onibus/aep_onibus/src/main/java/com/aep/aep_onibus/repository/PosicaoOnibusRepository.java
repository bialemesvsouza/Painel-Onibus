package com.aep.aep_onibus.repository;

import com.aep.aep_onibus.model.PosicaoOnibus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PosicaoOnibusRepository extends JpaRepository<PosicaoOnibus, Integer> {
    
    @Query(value = "SELECT p FROM PosicaoOnibus p WHERE p.onibus.id = :onibusId ORDER BY p.dataHora DESC LIMIT 1")
    PosicaoOnibus findFirstByOnibusIdOrderByDataHoraDesc(@Param("onibusId") Integer onibusId);
    
    List<PosicaoOnibus> findByOnibusLinhaIdOrderByDataHoraDesc(Integer linhaId);
}