package com.organizai.app.evento;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.organizai.app.usuario.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static List<EventoDTO> buildEventosDTOs(Usuario usuario) {
        List<Evento> eventos = usuario.getEventos();
        List<EventoDTO> eventoDTOs = new ArrayList<>();

        for (Evento evento : eventos) {
            EventoDTO eventoDTO = new EventoDTO(
                    evento.getTitulo(),
                    evento.getDescricao(),
                    evento.getData_inicio(),
                    evento.getData_fim(),
                    evento.getLocalizacao()
            );
            eventoDTOs.add(eventoDTO);
        }
        System.out.println(eventoDTOs);

        for (Evento evento : eventos) {
            String corpoDoEvento = evento.getCorpo(); // Substitua getCorpo() pelo método real
            System.out.println(corpoDoEvento);
        }
        return eventoDTOs;
    }

    // getters e setters
}
