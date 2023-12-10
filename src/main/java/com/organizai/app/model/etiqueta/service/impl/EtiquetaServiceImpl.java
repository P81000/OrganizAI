package com.organizai.app.model.etiqueta.service.impl;

import com.organizai.app.model.evento.Evento;
import com.organizai.app.model.evento.EventoDTO;
import com.organizai.app.model.etiqueta.*;
import com.organizai.app.model.etiqueta.Etiqueta;
import com.organizai.app.model.etiqueta.mapper.EtiquetaMapper;
import com.organizai.app.model.etiqueta.service.EtiquetaService;
import com.organizai.app.model.etiqueta.EtiquetaRepository;
import com.organizai.app.model.evento.EventoRepository;
import com.organizai.app.model.tarefa.Tarefa;
import com.organizai.app.model.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EtiquetaServiceImpl implements EtiquetaService {

    //private final EventoRepository eventoRepository;
    private final EtiquetaRepository etiquetaRepository;

    @Autowired
    public EtiquetaServiceImpl(EtiquetaRepository etiquetaRepository, EventoRepository eventoRepository) {
        this.etiquetaRepository = etiquetaRepository;
        //this.eventoRepository = eventoRepository;
    }

    @Override
    public List<EtiquetaDTO> findAllEtiquetas() {
        List<Etiqueta> etiquetas = etiquetaRepository.findAll();
        List<EtiquetaDTO> etiquetasDTO = etiquetas.stream().map(EtiquetaMapper::mapToEtiquetaDTO).collect(Collectors.toList());

        return etiquetasDTO;
    }

    @Override
    public Etiqueta saveEtiqueta(Etiqueta etiqueta) {
        System.out.println("etiquetaSave: " + etiqueta.getCorpo());
        return etiquetaRepository.save(etiqueta);
    }

    @Override
    public EtiquetaDTO findEtiquetaById(Integer IdEtiqueta) {
        Etiqueta etiqueta = etiquetaRepository.findById(IdEtiqueta).get();
        return EtiquetaMapper.mapToEtiquetaDTO(etiqueta);
    }

    @Override
    public void updateEtiqueta(Etiqueta etiqueta) {

    }

    @Override
    public void deleteEtiqueta(Integer IdEtiqueta) {
        etiquetaRepository.deleteById(IdEtiqueta);
    }

    @Override
    public List<EtiquetaDTO> searchEtiquetas(String query) {
        return null;
    }

    @Override
    public void addEventoInEtiqueta(Integer IdEtiqueta, Evento evento){
        Etiqueta etiqueta = etiquetaRepository.findById(IdEtiqueta).get();
        etiqueta.addEvento(evento);
    }

    @Override
    public void deleteAllEtiquetasByUsuario(Usuario usuario){
        List<Etiqueta> etiquetas = usuario.getEtiquetas();
        for(Etiqueta etiqueta: etiquetas){
            int idEtiqueta = etiqueta.getIdEtiqueta();
            etiquetaRepository.deleteById(idEtiqueta);
        }
    }
}
