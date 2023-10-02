package com.organizai.app.usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.organizai.app.evento.Evento;
import jakarta.persistence.*;
import lombok.Getter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.organizai.app.model.LoginRequest; // Substitua "model" pelo pacote real onde a classe está definida


@Getter
@Entity
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

    public Usuario() {

    }

    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }


    private static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
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

    public void storePassword(String password) throws NoSuchAlgorithmException {
        byte[] salt = generateSalt();

        // Armazene a senha e o salt no banco de dados
        this.password = hashPassword(password, salt);
        // Armazene o salt para uso posterior na validação
        this.salt = salt;
    }

    public Usuario(int idUsuario, String userName, String pwd) throws NoSuchAlgorithmException {
        id_usuario = idUsuario;
        username = userName;
        setPassword(pwd);
    }


    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {

        storePassword(password);

        //this.password = password;
    }

    public void setEventos(Evento eventos) {
        this.eventos.add(eventos);
    }

    public String getPassword() throws NoSuchAlgorithmException {

        return password;
    }

}
