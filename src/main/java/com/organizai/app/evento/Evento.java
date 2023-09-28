package com.organizai.app.evento;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.organizai.app.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;


@Entity
public class Evento {

      @Getter
      @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int id_evento;
      @Getter
      private String titulo;
      @Getter
      private String descricao;
      @Getter
      private String data_inicio;
      @Getter
      private String data_fim;
      @Getter
      private String localizacao;
      private String evento_status;
      @JsonIgnore
      @ManyToOne
      @JoinColumn(name="id_usuario")
      private Usuario _usuario;
      @Getter
      private String id_notificacao;
      @Getter
      private String id_info_clima;
      @Getter
      private String id_info_trajeto;

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
      public void setLocalizacao(String localizacao) {
            this.localizacao = localizacao;
      }


      public Usuario getUsuario() {
            return _usuario;
      }

      public void set_usuario(Usuario _usuario) {
            this._usuario = _usuario;
      }
      public String getCorpo() {

          return "Título: " + titulo + "\n" +
                    "Descrição: " + descricao + "\n" +
                    "Data de Início: " + data_inicio + "\n" +
                    "Data de Fim: " + data_fim + "\n" +
                    "Localização: " + localizacao + "\n";
      }




      public void setId_notificacao(String id_notificacao) {
            this.id_notificacao = id_notificacao;
      }

      public void setId_info_clima(String id_info_clima) {
            this.id_info_clima = id_info_clima;
      }

      public void setId_info_trajeto(String id_info_trajeto) {
            this.id_info_trajeto = id_info_trajeto;
      }

      public void setEvento_status(String evento_status) {
            this.evento_status = evento_status;
      }
      
}
