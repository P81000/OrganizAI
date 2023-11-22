package com.organizai.app.model.etiqueta;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.organizai.app.model.evento.Evento;
import com.organizai.app.model.tarefa.Tarefa;
import com.organizai.app.model.usuario.Usuario;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Builder
@Getter
public class EtiquetaDTO {

    @JsonProperty("id")
    public int id;

    @JsonProperty("cor")
    public String cor;

    @JsonProperty("nome")
    public String nome;

    @JsonProperty("prioridade")
    public int prioridade;

    @JsonProperty("eventos")
    public List<Evento> eventos;

    // Construtores, getters e setters

    public EtiquetaDTO(Integer id, String cor, String nome, Integer prioridade, List<Evento> eventos) {
        this.id = id;
        this.cor = cor;
        this.nome = nome;
        this.prioridade = prioridade;
        this.eventos = eventos;
    }

    public static List<EtiquetaDTO> buildEtiquetasDTOs(Usuario usuario) {
        List<Etiqueta> etiquetas = usuario.getEtiquetas();
        List<EtiquetaDTO> etiquetaDTOs = new ArrayList<>();

        for (Etiqueta etiqueta : etiquetas) {
            EtiquetaDTO etiquetaDTO = new EtiquetaDTO(
                    etiqueta.getIdEtiqueta(),
                    etiqueta.getCor(),
                    etiqueta.getNome(),
                    etiqueta.getPrioridade(),
                    etiqueta.getEventos()
            );
            etiquetaDTOs.add(etiquetaDTO);
        }
        System.out.println(etiquetaDTOs);

        for (Etiqueta etiqueta : etiquetas) {
            String corpoDaEtiqueta = etiqueta.getCorpo(); // Substitua getCorpo() pelo método real
            System.out.println(corpoDaEtiqueta);
        }
        return etiquetaDTOs;
    }

    public String getCorpo() {

        return "Cor: " + cor + "\n" +
                "Nome:" + nome + "\n" +
                "Descrição: " + prioridade + "\n";
    }
}
