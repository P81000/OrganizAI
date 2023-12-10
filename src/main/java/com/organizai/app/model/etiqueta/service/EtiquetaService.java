package com.organizai.app.model.etiqueta.service;

import com.organizai.app.model.etiqueta.Etiqueta;
import com.organizai.app.model.etiqueta.EtiquetaDTO;
import com.organizai.app.model.evento.Evento;
import com.organizai.app.model.usuario.Usuario;

import java.util.List;

public interface EtiquetaService {

    List<EtiquetaDTO> findAllEtiquetas();

    Etiqueta saveEtiqueta(Etiqueta etiqueta);

    EtiquetaDTO findEtiquetaById(Integer IdEtiqueta);

    void updateEtiqueta(Etiqueta etiqueta);

    void deleteEtiqueta(Integer IdEtiqueta);

    List<EtiquetaDTO> searchEtiquetas(String query);

    void addEventoInEtiqueta(Integer IdEtiqueta, Evento evento);

    void deleteAllEtiquetasByUsuario(Usuario usuario);
}
