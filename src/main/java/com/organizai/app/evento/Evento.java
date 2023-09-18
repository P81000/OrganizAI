package com.organizai.app.evento;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "Evento")
@Entity(name = "Evento")
public class Evento {

      @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int id_evento;
      private String titulo;
      private String descricao;
      private String data_inicio;
      private String data_fim;
      private String localizacao;


      public String getEvento_status() {
            return evento_status;
      }

      public void setEvento_status(String evento_status) {
            this.evento_status = evento_status;
      }

      private String evento_status;
      private String id_usuario;
      private String id_notificacao;
      private String id_info_clima;
      private String id_info_trajeto;
      
      public int getId_evento() {
            return id_evento;
      }
      public void setId_evento(int id_evento) {
            this.id_evento = id_evento;
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
      public String getData_inicio() {
            return data_inicio;
      }
      public void setData_inicio(String data_inicio) {
            this.data_inicio = data_inicio;
      }
      public String getData_fim() {
            return data_fim;
      }
      public void setData_fim(String data_fim) {
            this.data_fim = data_fim;
      }
      public String getLocalizacao() {
            return localizacao;
      }
      public void setLocalizacao(String localizacao) {
            this.localizacao = localizacao;
      }


      public String getId_usuario() {
            return id_usuario;
      }
      public void setId_usuario(String id_usuario) {
            this.id_usuario = id_usuario;
      }
      public String getId_notificacao() {
            return id_notificacao;
      }
      public void setId_notificacao(String id_notificacao) {
            this.id_notificacao = id_notificacao;
      }
      public String getId_info_clima() {
            return id_info_clima;
      }
      public void setId_info_clima(String id_info_clima) {
            this.id_info_clima = id_info_clima;
      }
      public String getId_info_trajeto() {
            return id_info_trajeto;
      }
      public void setId_info_trajeto(String id_info_trajeto) {
            this.id_info_trajeto = id_info_trajeto;
      }
      
}
