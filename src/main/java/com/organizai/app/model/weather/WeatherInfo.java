package com.organizai.app.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.organizai.app.model.evento.Evento;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @JoinColumn(name="id_evento")
    @OneToOne(cascade = CascadeType.ALL)
    private Evento _evento;

    private double probDeChuva;
    private int umidade;
    private double tempMaxima;
    private double tempMinima;
    private double temp;
    private double sensacaoTermica;
    private String descricao;

    public WeatherInfo(){

    }
    public WeatherInfo(Evento evento, double probDeChuva, int umidade, double temp, double tempMaxima, double tempMinima, double sensacaoTermica, String descricao) {
        this._evento = evento;
        this.probDeChuva = probDeChuva;
        this.umidade = umidade;
        this.temp = temp - 273.15;
        this.tempMaxima = tempMaxima - 273.15;
        this.tempMinima = tempMinima - 273.15;
        this.sensacaoTermica = sensacaoTermica - 273.15;
        this.descricao = descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void set_evento(Evento evento) {
        this._evento = evento;
    }

    public void setProbDeChuva(double probDeChuva) {
        this.probDeChuva = probDeChuva;
    }

    public void setUmidade(int umidade) {
        this.umidade = umidade;
    }

    public void setTempMaxima(double tempMaxima) {
        this.tempMaxima = tempMaxima;
    }

    public void setTempMinima(double tempMinima) {
        this.tempMinima = tempMinima;
    }

    public void setSensacaoTermica(double sensacaoTermica) {
        this.sensacaoTermica = sensacaoTermica;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

