package com.organizai.app.model.tarefa.mapper;

import com.organizai.app.model.tarefa.TarefaDTO;
import com.organizai.app.model.tarefa.Tarefa;

public class TarefaMapper {
    public static TarefaDTO mapToTarefaDTO(Tarefa tarefa) {
        TarefaDTO tarefaDTO = TarefaDTO.builder()
                .id(tarefa.getIdTarefa())
                .titulo(tarefa.getTitulo())
                .descricao(tarefa.getDescricao())
                .status(tarefa.getStatus())
                .build();
        return tarefaDTO;
    }
}