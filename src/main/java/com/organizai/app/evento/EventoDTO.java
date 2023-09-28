package com.organizai.app.evento;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class EventoDTO {
    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("data_inicio")
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private String dataInicio;

    @JsonProperty("data_fim")
   // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private String dataFim;

    @JsonProperty("localizacao")
    private String localizacao;

    // Construtores, getters e setters

    public EventoDTO(String titulo, String descricao, String dataInicio, String dataFim, String localizacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.localizacao = localizacao;
    }

    // getters e setters
}
