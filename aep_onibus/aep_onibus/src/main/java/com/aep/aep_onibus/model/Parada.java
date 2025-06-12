package com.aep.aep_onibus.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Parada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String codigo;
    private String nome;
    private Double latitude;
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "linha_id")
    @JsonBackReference("linha-parada")
    private Linha linha;

    @OneToMany(mappedBy = "parada")
    @JsonManagedReference("parada-painel")
    private List<Painel> paineis;


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

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public List<Painel> getPaineis() {
        return paineis;
    }

    public void setPaineis(List<Painel> paineis) {
        this.paineis = paineis;
    }
}
