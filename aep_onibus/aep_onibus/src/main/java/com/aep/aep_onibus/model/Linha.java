package com.aep.aep_onibus.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Linha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String codigo;
    private String nome;
    private String origem;
    private String destino;

    @OneToMany(mappedBy = "linha")
    @JsonManagedReference("linha-onibus")
    private List<Onibus> onibus;

    @OneToMany(mappedBy = "linha")
    @JsonManagedReference("linha-parada")
    private List<Parada> paradas;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public List<Onibus> getOnibus() {
        return onibus;
    }

    public void setOnibus(List<Onibus> onibus) {
        this.onibus = onibus;
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public void setParadas(List<Parada> paradas) {
        this.paradas = paradas;
    }
}