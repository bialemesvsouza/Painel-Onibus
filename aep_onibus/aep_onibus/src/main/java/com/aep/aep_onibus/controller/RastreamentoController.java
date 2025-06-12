package com.aep.aep_onibus.controller;

import com.aep.aep_onibus.model.Onibus;
import com.aep.aep_onibus.model.PosicaoOnibus;
import com.aep.aep_onibus.repository.OnibusRepository;
import com.aep.aep_onibus.repository.PosicaoOnibusRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/rastreamento")
public class RastreamentoController {

    private final PosicaoOnibusRepository posicaoRepository;
    private final OnibusRepository onibusRepository;

    public RastreamentoController(PosicaoOnibusRepository posicaoRepository,
                                  OnibusRepository onibusRepository) {
        this.posicaoRepository = posicaoRepository;
        this.onibusRepository = onibusRepository;
    }

    @PostMapping("/atualizar")
    public ResponseEntity<PosicaoOnibus> atualizarPosicao(
            @RequestBody PosicaoDTO posicaoDTO) {

        Onibus onibus = onibusRepository.findByCodigo(posicaoDTO.getCodigoOnibus());
        if (onibus == null) {
            return ResponseEntity.notFound().build();
        }

        PosicaoOnibus posicao = new PosicaoOnibus();
        posicao.setOnibus(onibus);
        posicao.setLatitude(posicaoDTO.getLatitude());
        posicao.setLongitude(posicaoDTO.getLongitude());
        posicao.setDataHora(LocalDateTime.now());

        PosicaoOnibus saved = posicaoRepository.save(posicao);
        return ResponseEntity.ok(saved);
    }

    public static class PosicaoDTO {
        private String codigoOnibus;
        private Double latitude;
        private Double longitude;

        public String getCodigoOnibus() {
            return codigoOnibus;
        }

        public void setCodigoOnibus(String codigoOnibus) {
            this.codigoOnibus = codigoOnibus;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }
    }
}
