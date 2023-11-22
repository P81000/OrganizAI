package com.organizai.app.model.usuario.service;

import com.organizai.app.model.usuario.Usuario;

public interface UsuarioService {

    Usuario saveUser(Usuario usuario);
    Usuario findById(Integer id);
    Usuario findByEmail(String email);
    Usuario findByUsername(String username);

    void deleteById(Integer id);

    boolean existsById(Integer id);
}
