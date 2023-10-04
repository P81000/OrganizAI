package com.organizai.app.evento;

import com.organizai.app.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    @Override
    Evento getById(Integer integer);
}
