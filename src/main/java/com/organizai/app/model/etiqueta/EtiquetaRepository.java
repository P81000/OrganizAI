package com.organizai.app.model.etiqueta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EtiquetaRepository extends JpaRepository<Etiqueta, Integer> {
    @Override
    Etiqueta getById(Integer integer);
}