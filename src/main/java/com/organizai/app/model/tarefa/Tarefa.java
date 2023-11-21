package com.organizai.app.model.tarefa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.organizai.app.model.evento.Evento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTarefa;
    private String titulo;
    private String descricao;
    private String status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_evento")
    private Evento _evento;

    public Evento get_evento() {
        return _evento;
    }

    public void set_evento(Evento _evento) {
        this._evento = _evento;
    }

    public String getCorpo() {

        return "Título: " + titulo + "\n" +
                "Descrição: " + descricao + "\n" +
                "Status: " + status + "\n";
    }
}
