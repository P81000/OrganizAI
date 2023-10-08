package com.organizai.app.model.evento;

import com.organizai.app.model.evento.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
      
}
