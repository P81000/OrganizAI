package com.organizai.app.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.organizai.app.model.evento.Evento;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@JsonIgnoreProperties
public class WeatherInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @OneToOne(mappedBy = "infoClima", cascade = CascadeType.ALL)
    @JsonIgnore
    private Evento evento;

    private String cod;
    private int message;

    private long dt;
    private double temp;

    private double temp_min;
    private double temp_max;
    private int humidity;

    @JsonProperty("feels_like")
    private double sensacaoTermica;

    private String mainWeather;
    private String description;
    private int clouds; //porcentagem de nuvens no ceu
    private double windSpeed;
    private double visibility;
    @JsonProperty("pop")
    private double chanceChuva; //precipitação
    private String cityName;
    private String country;
    private int population;
    private int timezone;

    public void setId(int id) {
        this.id = id;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setSensacaoTermica(double sensacaoTermica) {
        this.sensacaoTermica = sensacaoTermica;
    }

    public void setMainWeather(String mainWeather) {
        this.mainWeather = mainWeather;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public void setChanceChuva(double chanceChuva) {
        this.chanceChuva = chanceChuva;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }
}

