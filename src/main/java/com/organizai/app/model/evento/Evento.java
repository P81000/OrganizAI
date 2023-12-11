package com.organizai.app.model.evento;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.organizai.app.model.etiqueta.Etiqueta;
import com.organizai.app.model.tarefa.Tarefa;
import com.organizai.app.model.usuario.Usuario;
import com.organizai.app.model.weather.WeatherInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
public class Evento {

      @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int id_evento;
      private String titulo;
      private String descricao;
      private String data_inicio;
      private String data_fim;
      private String cidade;
      private String estado;
      private String evento_status;
      @JsonIgnore
      @ManyToOne
      @JoinColumn(name="id_usuario")
      private Usuario _usuario;
      private String id_notificacao;

      @JsonIgnore
      @OneToOne(cascade = CascadeType.ALL)
      @JoinColumn(name="weather_info_id")
      private WeatherInfo infoClima;
      private String id_info_trajeto;

      @JsonIgnore
      @ManyToOne
      @JoinColumn(name="id_etiqueta")
      private Etiqueta _etiqueta;

      @JsonIgnore
      @OneToMany(mappedBy = "_evento", cascade = CascadeType.ALL)
      private List<Tarefa> tarefas = new ArrayList<>();

      public void addTarefa(Tarefa tarefa) {
            this.tarefas.add(tarefa);
      }

      public void setId_evento(int id_evento) {
            this.id_evento = id_evento;
      }

      public void setTitulo(String titulo) {
            this.titulo = titulo;
      }

      public void setDescricao(String descricao) {
            this.descricao = descricao;
      }
      public void setData_inicio(String data_inicio) {
            this.data_inicio = data_inicio;
      }

      public void setData_fim(String data_fim) {
            this.data_fim = data_fim;
      }

      public void set_usuario(Usuario _usuario) {
            this._usuario = _usuario;
      }
      public String getCorpo() {

            return "Título: " + titulo + "\n" +
                    "Id: " + id_evento + "\n" +
                    "Descrição: " + descricao + "\n" +
                    "Data de Início: " + data_inicio + "\n" +
                    "Data de Fim: " + data_fim + "\n" +
                    "Localização: " + cidade +"/"+ estado + "\n" +
                    "Tarefas: " + tarefas + "\n" +
                    "Notificação: " + id_notificacao + "\n" +
                    "Clima: " + infoClima + "\n" +
                    "Trajeto: " + id_info_trajeto + "\n" +
                    "Usuário: " + _usuario + "\n" +
                    "Status: " + evento_status + "\n";

//          return "Título: " + titulo + "\n" +
//                    "Descrição: " + descricao + "\n" +
//                    "Data de Início: " + data_inicio + "\n" +
//                    "Data de Fim: " + data_fim + "\n" +
//                    "Localização: " + cidade +"/"+ estado + "\n" +
//                    "Tarefas: " + tarefas + "\n" +
//                    "Notificação: " + id_notificacao + "\n" +
//                    "Clima: " + infoClima + "\n" +
//                    "Trajeto: " + id_info_trajeto + "\n" +
//                    "Status: " + evento_status + "\n";
      }

      public void setId_notificacao(String id_notificacao) {
            this.id_notificacao = id_notificacao;
      }

      public void set_info_clima(WeatherInfo info_clima) {
            this.infoClima = info_clima;
      }

      public void setId_info_trajeto(String id_info_trajeto) {
            this.id_info_trajeto = id_info_trajeto;
      }

      public void setEvento_status(String evento_status) {
            this.evento_status = evento_status;
      }

      public void setCidade(String cidade) {
            this.cidade = cidade;
      }

      public void setEstado(String estado) {
            this.estado = estado;
      }
}
