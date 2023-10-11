package com.organizai.app.model.tarefa.service.impl;

import com.organizai.app.model.evento.Evento;
import com.organizai.app.model.evento.EventoDTO;
import com.organizai.app.model.tarefa.*;
import com.organizai.app.model.tarefa.Tarefa;
import com.organizai.app.model.tarefa.mapper.TarefaMapper;
import com.organizai.app.model.tarefa.service.TarefaService;
import com.organizai.app.model.tarefa.TarefaRepository;
import com.organizai.app.model.evento.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaServiceImpl implements TarefaService {

    private final EventoRepository eventoRepository;
    private final TarefaRepository tarefaRepository;

    @Autowired
    public TarefaServiceImpl(TarefaRepository tarefaRepository, EventoRepository eventoRepository) {
        this.tarefaRepository = tarefaRepository;
        this.eventoRepository = eventoRepository;
    }

    @Override
    public List<TarefaDTO> findAllTarefas() {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        List<TarefaDTO> tarefasDTO = tarefas.stream().map(TarefaMapper::mapToTarefaDTO).collect(Collectors.toList());

        return tarefasDTO;
    }

    @Override
    public Tarefa saveTarefa(Tarefa tarefa) {
        System.out.println("tarefaSave: " + tarefa.getCorpo());
        return tarefaRepository.save(tarefa);
    }

    @Override
    public TarefaDTO findTarefaById(Integer tarefaId) {
        Tarefa tarefa = tarefaRepository.findById(tarefaId).get();
        return TarefaMapper.mapToTarefaDTO(tarefa);
    }

    @Override
    public void updateTarefa(Tarefa tarefa) {

    }

    @Override
    public void deleteTarefa(Integer tarefaId) {
        tarefaRepository.deleteById(tarefaId);
    }

    @Override
    public List<TarefaDTO> searchTarefas(String query) {
        return null;
    }

    public void deleteAllTarefasByEvento(EventoDTO evento){
        List<Tarefa> tarefas = evento.getTarefas();
        for(Tarefa tarefa: tarefas){
            int idTarefa = tarefa.getIdTarefa();
            tarefaRepository.deleteById(idTarefa);
        }
    }

}
