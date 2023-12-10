package com.organizai.app.model.usuario.service.impl;

import com.organizai.app.model.usuario.Usuario;
import com.organizai.app.model.usuario.UsuarioRepository;
import com.organizai.app.model.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;


    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario saveUser(Usuario usuario) {
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioSalvo;
    }

    @Override
    public Usuario findById(Integer id) {
        Usuario usuario = usuarioRepository.getById(id);
        return usuario;
    }

    @Override
    public Usuario findByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        return usuario;
    }

    @Override
    public Usuario findByUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        return usuario;
    }

    @Override
    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return usuarioRepository.existsById(id);
    }
}
