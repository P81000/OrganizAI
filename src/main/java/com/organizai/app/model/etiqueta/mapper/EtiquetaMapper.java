package com.organizai.app.model.etiqueta.mapper;

import com.organizai.app.model.etiqueta.EtiquetaDTO;
import com.organizai.app.model.etiqueta.Etiqueta;

public class EtiquetaMapper {
    public static EtiquetaDTO mapToEtiquetaDTO(Etiqueta etiqueta) {
        EtiquetaDTO etiquetaDTO = EtiquetaDTO.builder()
                .id(etiqueta.getIdEtiqueta())
                .cor(etiqueta.getCor())
                .nome(etiqueta.getNome())
                .prioridade(etiqueta.getPrioridade())
                .eventos(etiqueta.getEventos())
                .build();
        return etiquetaDTO;
    }
}
