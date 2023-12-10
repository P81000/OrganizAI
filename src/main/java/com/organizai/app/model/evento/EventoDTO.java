package com.organizai.app.model.evento;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.organizai.app.model.tarefa.Tarefa;
import com.organizai.app.model.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Builder
@Getter
public class EventoDTO {

    @JsonProperty("id")
    public int id;

    @JsonProperty("titulo")
    public String titulo;

    @JsonProperty("descricao")
    public String descricao;

    @JsonProperty("data_inicio")
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    public String dataInicio;

    @JsonProperty("data_fim")
   // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    public String dataFim;

    @JsonProperty("cidade")
    public String cidade;

    @JsonProperty("estado")
    public String estado;

    @JsonProperty("tarefas")
    public List<Tarefa> tarefas;

    // Construtores, getters e setters

    public EventoDTO(Integer id, String titulo, String descricao, String dataInicio, String dataFim, String cidade, String estado, List<Tarefa> tarefas) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.cidade = cidade;
        this.estado = estado;
        this.tarefas = tarefas;
    }

    public static List<EventoDTO> buildEventosDTOs(Usuario usuario) {
        List<Evento> eventos = usuario.getEventos();
        List<EventoDTO> eventoDTOs = new ArrayList<>();

        for (Evento evento : eventos) {
            EventoDTO eventoDTO = new EventoDTO(
                    evento.getId_evento(),
                    evento.getTitulo(),
                    evento.getDescricao(),
                    evento.getData_inicio(),
                    evento.getData_fim(),
                    evento.getCidade(),
                    evento.getEstado(),
                    evento.getTarefas()
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

    public String getCorpo() {
        return "{\n" +
                "\"Id\": " + id + ",\n" +
                "\"Titulo\": \"" + titulo + "\",\n" +
                "\"Descricao\": \"" + descricao + "\",\n" +
                "\"Data de Inicio\": \"" + dataInicio + "\",\n" +
                "\"Data de Fim\": \"" + dataFim + "\",\n" +
                "\"Localizacao\": \"" + cidade + "\",\n" +
                "\"Tarefas\": " + tarefas + "\n" +
                "},\n";
    }
}
