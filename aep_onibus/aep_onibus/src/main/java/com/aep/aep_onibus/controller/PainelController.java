package com.aep.aep_onibus.controller;

import com.aep.aep_onibus.model.Linha;
import com.aep.aep_onibus.model.Parada;
import com.aep.aep_onibus.model.PosicaoOnibus;
import com.aep.aep_onibus.model.Onibus; // Importação adicionada
import com.aep.aep_onibus.repository.LinhaRepository;
import com.aep.aep_onibus.repository.ParadaRepository;
import com.aep.aep_onibus.repository.PosicaoOnibusRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
        
        List<Onibus> onibusDaLinha = linha.getOnibus();
        List<Map<String, Object>> previsoes = new ArrayList<>();

        for (Onibus onibus : onibusDaLinha) {
            PosicaoOnibus ultimaPosicao = posicaoRepository
                .findFirstByOnibusIdOrderByDataHoraDesc(onibus.getId());
                
            if (ultimaPosicao != null) {
                Map<String, Object> previsao = new HashMap<>();
                previsao.put("onibus", onibus.getCodigo());
                previsao.put("ultimaAtualizacao", ultimaPosicao.getDataHora());

                double distancia = calcularDistancia(
                    ultimaPosicao.getLatitude(), ultimaPosicao.getLongitude(),
                    parada.getLatitude(), parada.getLongitude()
                );

                previsao.put("distanciaMetros", distancia);
                
                if (distancia < 50) {
                    previsao.put("tempoEstimadoMinutos", 0);
                    previsao.put("status", "CHEGANDO");
                } else {
                    int tempoEstimado = (int)(distancia / 400);
                    previsao.put("tempoEstimadoMinutos", tempoEstimado);
                    previsao.put("status", "EM_ROTA"); // Aspas corrigidas
                }
                
                previsoes.add(previsao);
            }
        }

        previsoes.sort(Comparator.comparingInt(p -> (Integer)p.get("tempoEstimadoMinutos")));

        Map<String, Object> response = new HashMap<>();
        response.put("parada", parada.getNome());
        response.put("linha", linha.getNome());
        response.put("previsoes", previsoes);

        return ResponseEntity.ok(response);
    }

    private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
                
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c * 1000;
    }
}