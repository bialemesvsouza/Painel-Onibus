package com.aep.aep_onibus.controller;

import com.aep.aep_onibus.model.Onibus;
import com.aep.aep_onibus.service.OnibusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/onibus")
public class OnibusController {

    private final OnibusService service;

    public OnibusController(OnibusService service) {
        this.service = service;
    }

    @GetMapping
    public List<Onibus> listarTodos() {
        return service.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Onibus> buscarPorCodigo(@PathVariable String codigo) {
        Onibus onibus = service.findByCodigo(codigo);
        return onibus != null ? ResponseEntity.ok(onibus) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Onibus criar(@RequestBody Onibus onibus) {
        return service.save(onibus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
