package com.organizai.app.model.evento.service;

import com.organizai.app.model.etiqueta.Etiqueta;
import com.organizai.app.model.evento.Evento;
import com.organizai.app.model.evento.EventoDTO;
import com.organizai.app.model.tarefa.Tarefa;
import com.organizai.app.model.usuario.Usuario;

import java.util.List;

public interface EventoService {

    List<EventoDTO> findAllEventos();

    Evento saveEvento(Evento evento);

    EventoDTO findEventoById(Integer eventoId);

    List<Evento> findEventosByEtiqueta(Etiqueta etiqueta);

    void updateEvento(Evento evento);

    void delete(Integer eventoId);

    List<EventoDTO> searchEventos(String query);

    void addTarefaInEvento(Integer eventoId, Tarefa tarefa);

    void deleteAllEventosByUsuario(Usuario usuario);

    Evento convertDTOToEntity(EventoDTO eventoDTO);
}
