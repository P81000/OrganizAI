package com.organizai.app.model.tarefa.service;

import com.organizai.app.model.tarefa.Tarefa;
import com.organizai.app.model.tarefa.TarefaDTO;

import java.util.List;

public interface TarefaService {

    List<TarefaDTO> findAllTarefas();

    Tarefa saveTarefa(Tarefa tarefa);

    TarefaDTO findTarefaById(Integer tarefaId);

    void updateTarefa(Tarefa tarefa);

    void deleteTarefa(Integer tarefaId);

    List<TarefaDTO> searchTarefas(String query);

}
