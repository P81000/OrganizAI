package com.organizai.app.controller;

import com.organizai.app.model.login.LoginRequest;
import com.organizai.app.model.usuario.Usuario;
import com.organizai.app.model.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.NoSuchAlgorithmException;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    @Autowired
    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @RequestMapping("/login")
    public String login() {
        //System.out.println("teste");
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws NoSuchAlgorithmException {

        Usuario usuario = usuarioService.findByEmail(loginRequest.getEmail());
        System.out.println("Cheguei aqui");

        if (usuario == null) {
            System.out.println("Usuario nao encontrado!");// Usuário não encontrado, login falhou
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado.");
        }

        try {
            // Use a função de hash para calcular o hash da senha inserida no login
            String hashedPassword = Usuario.hashPassword(loginRequest.getPassword(), usuario.getSalt());

            if (hashedPassword.equals(usuario.getPassword())) {
                System.out.println("Login Aceito!\n");
                return ResponseEntity.ok("redirect:/eventos");
            } else {
                System.out.println("Senha inválida.");
                System.out.println(loginRequest.getPassword());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha inválida.");
            }
        } catch (NoSuchAlgorithmException e) {
            // Tratar exceção de algoritmo de hash não suportado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno.");
        }
    }
}



