package com.organizai.app.model.usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.organizai.app.model.etiqueta.Etiqueta;
import com.organizai.app.model.evento.Evento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;
    private String username;
    private String email;
    private String password;
    private byte[] salt;

    @Getter
    @JsonIgnore
    @OneToMany(mappedBy = "_usuario", cascade = CascadeType.ALL)
    private List<Evento> eventos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "_usuario", cascade = CascadeType.ALL)
    private List<Etiqueta> etiquetas = new ArrayList<>();

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() throws NoSuchAlgorithmException {

        return password;
    }
    public void setEventos(Evento eventos) {
        this.eventos.add(eventos);
    }
    public void setEtiquetas(Etiqueta etiqueta) {
        this.etiquetas.add(etiqueta);
    }
    public void setPassword(String password) throws NoSuchAlgorithmException {

        byte[] salt = generateSalt();
        this.password = hashPassword(password, salt);
        this.salt = salt;

    }

    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }


    public static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);
        byte[] hashedBytes = digest.digest(password.getBytes());

        // Converte o array de bytes em uma representação hexadecimal
        StringBuilder builder = new StringBuilder();
        for (byte b : hashedBytes) {
            builder.append(String.format("%02x", b));
        }

        return builder.toString();
    }

}
