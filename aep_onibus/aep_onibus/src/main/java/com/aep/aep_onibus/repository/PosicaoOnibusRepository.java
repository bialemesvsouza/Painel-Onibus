package com.aep.aep_onibus.repository;


import com.aep.aep_onibus.model.PosicaoOnibus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PosicaoOnibusRepository extends JpaRepository<PosicaoOnibus, Integer> {
    List<PosicaoOnibus> findByOnibusLinhaIdOrderByDataHoraDesc(Integer OnibusLinhaIdOrderByDataHoraDesc);
}

