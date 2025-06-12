package com.aep.aep_onibus.controller;


import com.aep.aep_onibus.model.PosicaoOnibus;
import com.aep.aep_onibus.service.PosicaoOnibusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posicoes")
public class PosicaoOnibusController {

    private final PosicaoOnibusService service;

    public PosicaoOnibusController(PosicaoOnibusService service) {
        this.service = service;
    }

    @GetMapping
    public List<PosicaoOnibus> listarTodas() {
        return service.findAll();
    }

    @GetMapping("/onibus/{onibusId}")
    public List<PosicaoOnibus> buscarPorOnibusId(@PathVariable Integer onibusId) {
        return service.findByOnibusId(onibusId);
    }

    @PostMapping
    public PosicaoOnibus criar(@RequestBody PosicaoOnibus posicao) {
        return service.save(posicao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

