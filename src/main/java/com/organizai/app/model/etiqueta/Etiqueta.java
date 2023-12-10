package com.organizai.app.model.etiqueta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.organizai.app.model.evento.Evento;
import com.organizai.app.model.tarefa.Tarefa;
import com.organizai.app.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Etiqueta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEtiqueta;
    private String cor;
    private String nome;
    private int prioridade;

    @JsonIgnore
    @OneToMany(mappedBy = "_etiqueta", cascade = CascadeType.ALL)
    private List<Evento> eventos = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario _usuario;

    public void addEvento(Evento evento) {
        this.eventos.add(evento);
    }
    public String getCorpo() {

        return  "Cor: " + cor + "\n" +
                "Nome: " + nome + "\n" +
                "Prioridade: " + prioridade + "\n";
    }
}
