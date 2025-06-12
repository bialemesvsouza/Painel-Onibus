package com.aep.aep_onibus.controller;

import com.aep.aep_onibus.model.Linha;
import com.aep.aep_onibus.model.Parada;
import com.aep.aep_onibus.model.PosicaoOnibus;
import com.aep.aep_onibus.repository.LinhaRepository;
import com.aep.aep_onibus.repository.ParadaRepository;
import com.aep.aep_onibus.repository.PosicaoOnibusRepository;
import com.aep.aep_onibus.model.*;
import com.aep.aep_onibus.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/paineis")
public class PainelController {

    private final ParadaRepository paradaRepository;
    private final PosicaoOnibusRepository posicaoRepository;
    private final LinhaRepository linhaRepository;

    public PainelController(ParadaRepository paradaRepository,
                            PosicaoOnibusRepository posicaoRepository,
                            LinhaRepository linhaRepository) {
        this.paradaRepository = paradaRepository;
        this.posicaoRepository = posicaoRepository;
        this.linhaRepository = linhaRepository;
    }

    @GetMapping("/{codigoParada}/previsao")
    public ResponseEntity<?> getPrevisaoChegada(@PathVariable String codigoParada) {
        Parada parada = paradaRepository.findByCodigo(codigoParada);
        if (parada == null) {
            return ResponseEntity.notFound().build();
        }

        Linha linha = parada.getLinha();

        List<PosicaoOnibus> posicoes = posicaoRepository
                .findByOnibusLinhaIdOrderByDataHoraDesc(linha.getId());

        // Calcular distância e tempo estimado para cada ônibus
        List<Map<String, Object>> previsoes = posicoes.stream()
                .map(posicao -> {
                    Map<String, Object> previsao = new HashMap<>();
                    previsao.put("onibus", posicao.getOnibus().getCodigo());
                    previsao.put("ultimaAtualizacao", posicao.getDataHora());

                    // Simulação de cálculo de distância e tempo (implementar cálculo real)
                    double distancia = calcularDistancia(
                            posicao.getLatitude(), posicao.getLongitude(),
                            parada.getLatitude(), parada.getLongitude()
                    );

                    previsao.put("distanciaMetros", distancia);
                    previsao.put("tempoEstimadoMinutos", (int)(distancia / 400)); // 400m/min ≈ 24km/h

                    return previsao;
                })
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("parada", parada.getNome());
        response.put("linha", linha.getNome());
        response.put("previsoes", previsoes);

        return ResponseEntity.ok(response);
    }

    private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        // Implementação simplificada - considerar usar biblioteca como GeographicLib para cálculo preciso
        final int R = 6371; // Raio da Terra em km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c * 1000; // Distância em metros
    }
}
