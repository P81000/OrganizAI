package com.organizai.app.controller;

import com.organizai.app.model.etiqueta.service.EtiquetaService;
import com.organizai.app.model.evento.EventoDTO;
import com.organizai.app.model.evento.service.EventoService;
import com.organizai.app.model.login.LoginRequest; // Verifique o pacote real onde a classe está definida

import com.organizai.app.model.usuario.UsuarioRepository;
import com.organizai.app.model.usuario.Usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.organizai.app.model.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final EventoService eventoService;
    private final EtiquetaService etiquetaService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, EventoService eventoService, EtiquetaService etiquetaService) {
        this.usuarioService = usuarioService;
        this.eventoService = eventoService;
        this.etiquetaService = etiquetaService;
    }

    @RequestMapping("/cadastro")
    public String getUsuarios() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        if (usuario == null) return ResponseEntity.badRequest().build();

        // Verifique se o email já está cadastrado
        Usuario usuarioExistente = usuarioService.findByEmail(usuario.getEmail());
        if (usuarioExistente != null) {
            // O email já está em uso, retorne uma resposta de erro
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Você pode personalizar a resposta de erro conforme necessário
        }

        // Se o email não existe, continue com o cadastro
        Usuario novoUsuario = usuarioService.saveUser(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {

        if (!usuarioService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        }

        usuario.setId_usuario(id);
        var updatedUsuario = usuarioService.saveUser(usuario);

        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable Integer id) {
        if (!usuarioService.existsById(id)) return ResponseEntity.notFound().build();

        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build(); // Retorna status 404 Not Found se o evento não existe
        }

        eventoService.deleteAllEventosByUsuario(usuario);
        etiquetaService.deleteAllEtiquetasByUsuario(usuario);

        usuarioService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
