package com.organizai.app.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByUsername(String username);
    Usuario findByEmail(String email);

    @Override
    Usuario getById(Integer integer);
}
