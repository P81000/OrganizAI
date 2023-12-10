package com.organizai.app.model.tarefa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.organizai.app.model.evento.Evento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Builder
public class TarefaDTO {

    @JsonProperty("id")
    public int id;

    @JsonProperty("titulo")
    public String titulo;

    @JsonProperty("descricao")
    public String descricao;

    @JsonProperty("status")
    public String status;

    public TarefaDTO(Integer id, String titulo, String descricao, String status) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
    }

    public static List<TarefaDTO> buildTarefasDTOs(Evento evento) {
        List<Tarefa> tarefas = evento.getTarefas();
        List<TarefaDTO> tarefaDTOs = new ArrayList<>();

        for (Tarefa tarefa : tarefas) {
            TarefaDTO tarefaDTO = new TarefaDTO(
                    tarefa.getIdTarefa(),
                    tarefa.getTitulo(),
                    tarefa.getDescricao(),
                    tarefa.getStatus()
            );
            tarefaDTOs.add(tarefaDTO);
        }
        System.out.println(tarefaDTOs);

        for (Tarefa tarefa : tarefas) {
            String corpoDaTarefa = tarefa.getCorpo(); // Substitua getCorpo() pelo método real
            System.out.println(corpoDaTarefa);
        }
        return tarefaDTOs;
    }

    public String getCorpo() {

        return "Título: " + titulo + "\n" +
                "Descrição: " + descricao + "\n" +
                "Status: " + status + "\n";
    }
}
