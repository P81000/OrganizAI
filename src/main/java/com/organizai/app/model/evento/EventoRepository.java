package com.organizai.app.model.evento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    @Override
    Evento getById(Integer integer);
}
