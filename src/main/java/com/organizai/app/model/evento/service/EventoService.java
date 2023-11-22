package com.organizai.app.model.evento.service;

import com.organizai.app.model.evento.Evento;
import com.organizai.app.model.evento.EventoDTO;
import com.organizai.app.model.tarefa.Tarefa;

import java.util.List;

public interface EventoService {

    List<EventoDTO> findAllEventos();

    Evento saveEvento(Evento evento);

    EventoDTO findEventoById(Integer eventoId);

    void updateEvento(Evento evento);

    void delete(Integer eventoId);

    List<EventoDTO> searchEventos(String query);

    void addTarefaInEvento(Integer eventoId, Tarefa tarefa);
}
