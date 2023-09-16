package com.organizai.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.organizai.app.evento.EventoRepository;

import com.organizai.app.evento.Evento;

@RestController
@RequestMapping("agenda")
public class AgendaController {
      
      @Autowired
      private EventoRepository EventoRepository;

      @GetMapping
      public List<Evento> getAgenda() {
            
            //Eventos list
            List<Evento> Eventos = EventoRepository.findAll();
            return Eventos;

      }

}