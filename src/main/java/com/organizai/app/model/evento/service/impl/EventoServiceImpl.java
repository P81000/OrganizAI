package com.organizai.app.model.evento.service.impl;

import com.organizai.app.model.etiqueta.Etiqueta;
import com.organizai.app.model.evento.Evento;
import com.organizai.app.model.evento.EventoDTO;
import com.organizai.app.model.evento.mapper.EventoMapper;
import com.organizai.app.model.evento.service.EventoService;
import com.organizai.app.model.evento.EventoRepository;
import com.organizai.app.model.tarefa.Tarefa;
import com.organizai.app.model.usuario.Usuario;
import com.organizai.app.model.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoServiceImpl implements EventoService {

    private EventoRepository eventoRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public EventoServiceImpl(EventoRepository eventoRepository, UsuarioRepository usuarioRepository) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<EventoDTO> findAllEventos() {
        List<Evento> eventos = eventoRepository.findAll();
        List<EventoDTO> eventosDTO = eventos.stream().map(EventoMapper::mapToEventoDTO).collect(Collectors.toList());

        return eventosDTO;
    }

    @Override
    public Evento saveEvento(Evento evento) {
        //String username = SecurityUtil.getSessionUser();
        //Usuario usuario = usuarioRepository.findByUsername(username);
        //usuario.setEventos(evento);
        //novoEvento.set_usuario(usuario);

        //To-do implementar sessão

        System.out.println("eventoSave: " + evento.getCorpo());
        return eventoRepository.save(evento);
    }

    @Override
    public EventoDTO findEventoById(Integer eventoId) {
        Evento evento = eventoRepository.findById(eventoId).get();
        return EventoMapper.mapToEventoDTO(evento);
    }

    @Override
    public List<Evento> findEventosByEtiqueta(Etiqueta etiqueta) {
        List<Evento> eventos = etiqueta.getEventos();
        return eventos;
    }
    @Override
    public void updateEvento(Evento evento) {

    }

    @Override
    public void delete(Integer eventoId) {
        eventoRepository.deleteById(eventoId);
    }

    @Override
    public List<EventoDTO> searchEventos(String query) {
        return null;
    }

    @Override
    public void addTarefaInEvento(Integer idEvento, Tarefa tarefa){
        Evento evento = eventoRepository.findById(idEvento).get();
        evento.addTarefa(tarefa);
        eventoRepository.save(evento);
    }

    @Override
    public void deleteAllEventosByUsuario(Usuario usuario){
        List<Evento> eventos = usuario.getEventos();
        for(Evento evento: eventos){
            int idEvento = evento.getId_evento();
            eventoRepository.deleteById(idEvento);
        }
    }

    @Override
    public Evento convertDTOToEntity(EventoDTO eventoDTO) {
        if (eventoDTO == null) {
            return null;
        }

        Evento evento = eventoRepository.findById(eventoDTO.getId()).get();
        evento.setId_evento(eventoDTO.getId());
        evento.setTitulo(eventoDTO.getTitulo());
        evento.setDescricao(eventoDTO.getDescricao());
        evento.setData_inicio(eventoDTO.getDataInicio());
        evento.setData_fim(eventoDTO.getDataFim());
        evento.setCidade(eventoDTO.getCidade());
        evento.setEstado(eventoDTO.getEstado());

        return evento;
    }
}
