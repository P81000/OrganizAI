package com.organizai.app.usuario;

import com.organizai.app.evento.Evento;
import jakarta.persistence.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Table(name="Usuario")
@Entity(name="Usuario")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;
    private String username;

    private String email;
    private String password;
    @OneToMany(mappedBy = "_usuario", cascade = CascadeType.ALL)
    private List<Evento> eventos = new ArrayList<>();

    public Usuario() {

    }

    public Usuario(int idUsuario, String userName, String pwd){
        id_usuario = idUsuario;
        username = userName;
        password = pwd;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}
