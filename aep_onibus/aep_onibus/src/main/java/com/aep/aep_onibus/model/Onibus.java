package com.aep.aep_onibus.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Onibus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String codigo;
    private String placa;
    private String modelo;
    private Integer capacidade;

    @ManyToOne
    @JoinColumn(name = "linha_id")
    @JsonBackReference("linha-onibus")
    private Linha linha;

    @OneToMany(mappedBy = "onibus")
    @JsonManagedReference("onibus-posicao")
    private List<PosicaoOnibus> posicoes;


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

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public List<PosicaoOnibus> getPosicoes() {
        return posicoes;
    }

    public void setPosicoes(List<PosicaoOnibus> posicoes) {
        this.posicoes = posicoes;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }
}