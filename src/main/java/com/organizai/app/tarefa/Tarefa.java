package com.organizai.app.tarefa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.organizai.app.evento.Evento;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_tarefa;
    private String titulo;
    private String descricao;
    private Date horario_inicio;
    private Date horario_fim;
    private String tarefa_status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_evento")
    private Evento _evento;
    private int id_prioridade;
    private int id_status;
    private int id_board;
    private int id_feedback;

    public int getId_tarefa() {
        return id_tarefa;
    }

    public void setId_tarefa(int id_tarefa) {
        this.id_tarefa = id_tarefa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getHorario_inicio() {
        return horario_inicio;
    }

    public void setHorario_inicio(Date horario_inicio) {
        this.horario_inicio = horario_inicio;
    }

    public Date getHorario_fim() {
        return horario_fim;
    }

    public void setHorario_fim(Date horario_fim) {
        this.horario_fim = horario_fim;
    }

    public String getTarefa_status() {
        return tarefa_status;
    }

    public void setTarefa_status(String tarefa_status) {
        this.tarefa_status = tarefa_status;
    }

    public Evento get_evento() {
        return _evento;
    }

    public void set_evento(Evento _evento) {
        this._evento = _evento;
    }

    public String getCorpo() {

        return "Título: " + titulo + "\n" +
                "Descrição: " + descricao + "\n" +
                "Horário de Início: " + horario_inicio + "\n" +
                "Horário de Término: " + horario_fim + "\n" +
                "Status: " + tarefa_status + "\n";
    }

    public int getId_prioridade() {
        return id_prioridade;
    }

    public void setId_prioridade(int id_prioridade) {
        this.id_prioridade = id_prioridade;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public int getId_board() {
        return id_board;
    }

    public void setId_board(int id_board) {
        this.id_board = id_board;
    }

    public int getId_feedback() {
        return id_feedback;
    }

    public void setId_feedback(int id_feedback) {
        this.id_feedback = id_feedback;
    }
}
