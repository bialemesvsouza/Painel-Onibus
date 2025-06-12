package com.aep.aep_onibus.controller;

import com.aep.aep_onibus.model.Parada;
import com.aep.aep_onibus.service.ParadaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paradas")
public class ParadaController {

    private final ParadaService service;

    public ParadaController(ParadaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Parada> listarTodas() {
        return service.findAll();
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Parada> buscarPorNome(@PathVariable String nome) {
        Parada parada = service.findByNome(nome);
        return parada != null ? ResponseEntity.ok(parada) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Parada criar(@RequestBody Parada parada) {
        return service.save(parada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

