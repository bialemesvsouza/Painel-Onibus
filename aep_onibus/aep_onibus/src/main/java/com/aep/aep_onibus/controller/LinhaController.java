package com.aep.aep_onibus.controller;

import com.aep.aep_onibus.model.Linha;
import com.aep.aep_onibus.service.LinhaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/linhas")
public class LinhaController {

    private final LinhaService service;

    public LinhaController(LinhaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Linha> listarTodas() {
        return service.findAll();
    }

    @GetMapping("/{numero}")
    public ResponseEntity<Linha> buscarPorNumero(@PathVariable String numero) {
        Linha linha = service.findByNumero(numero);
        return linha != null ? ResponseEntity.ok(linha) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Linha criar(@RequestBody Linha linha) {
        return service.save(linha);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
