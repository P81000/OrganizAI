package com.organizai.app.model.evento.mapper;

import com.organizai.app.model.evento.EventoDTO;
import com.organizai.app.model.evento.Evento;

public class EventoMapper {
    public static EventoDTO mapToEventoDTO(Evento evento) {
        EventoDTO eventoDTO = EventoDTO.builder()
                .id(evento.getId_evento())
                .titulo(evento.getTitulo())
                .descricao(evento.getDescricao())
                .dataInicio(evento.getData_inicio())
                .dataFim(evento.getData_fim())
                .cidade(evento.getCidade())
                .estado(evento.getEstado())
                .tarefas(evento.getTarefas())
                .build();
        return eventoDTO;
    }
}
