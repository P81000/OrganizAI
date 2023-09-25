package com.organizai.app.controller;

import com.organizai.app.usuario.UsuarioRepository;
import com.organizai.app.usuario.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository _usuarioRepository;

    @GetMapping
    public List<Usuario> getUsuarios(){
        return _usuarioRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){
        if(usuario == null) return ResponseEntity.badRequest().build();

        Usuario novoUsuario = _usuarioRepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario){
        if(!_usuarioRepository.existsById(id)) return ResponseEntity.notFound().build();

        if(usuario == null) return ResponseEntity.badRequest().build();

        usuario.setId_usuario(id);

        var updatedUsuario = _usuarioRepository.save(usuario);

        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable Integer id){
        if(!_usuarioRepository.existsById(id)) return ResponseEntity.notFound().build();

        _usuarioRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
