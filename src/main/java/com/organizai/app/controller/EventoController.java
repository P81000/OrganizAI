package com.organizai.app.controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.organizai.app.model.Geocode.Geocode;
import com.organizai.app.model.tarefa.Tarefa;
import com.organizai.app.model.tarefa.TarefaDTO;
import com.organizai.app.model.tarefa.service.TarefaService;
import com.organizai.app.model.evento.EventoDTO;
import com.organizai.app.model.evento.service.EventoService;
import com.organizai.app.model.usuario.Usuario;
import com.organizai.app.model.usuario.service.UsuarioService;
import com.organizai.app.model.weather.WeatherInfo;
import com.organizai.app.model.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.organizai.app.model.evento.Evento;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;
    private final UsuarioService usuarioService;
    private final TarefaService tarefaService;
    private final WeatherService weatherService;
    @Value("${weather.api.key}")
    private String _geocodeApiKey;

    @Autowired
    public EventoController(EventoService eventoService, UsuarioService usuarioService, TarefaService tarefaService, WeatherService weatherService) {
        this.eventoService = eventoService;
        this.usuarioService = usuarioService;
        this.tarefaService = tarefaService;
        this.weatherService = weatherService;
    }

    @GetMapping
    public String getEvento(Model model) {

        List<EventoDTO> Eventos = eventoService.findAllEventos();
        model.addAttribute("eventos", Eventos);

        return "logado";
    }

    @PostMapping("/{email}")
    public ResponseEntity<Evento> createEvento(@PathVariable String email, @RequestBody Evento novoEvento) throws JsonProcessingException {
        // Verificar se é válido
        if (novoEvento == null) {
            return ResponseEntity.badRequest().build();
        }

        Usuario usuario = usuarioService.findByEmail(email);
        if (usuario == null) {
            return ResponseEntity.notFound().build(); // Retorna status 404 Not Found se o usuário não existe
        }

        Geocode geocodeParams = weatherService.getGeoCodeObject(novoEvento.getLocalizacao());
        //fazer a chamada pra api de tempo
        String weatherInfoJson = weatherService.getWeatherJson(geocodeParams.getLat(), geocodeParams.getLon());

        System.out.println(weatherInfoJson);

        ObjectMapper objectMapper = new ObjectMapper();
        WeatherInfo weatherInfoDesearilized = objectMapper.readValue(weatherInfoJson, WeatherInfo.class);

        System.out.println(weatherInfoDesearilized.getMessage());

        novoEvento.set_info_clima(weatherInfoDesearilized);
        weatherInfoDesearilized.setEvento(novoEvento);

        weatherService.SaveWeatherInfo(weatherInfoDesearilized);
        //salvar as informações de tempo atreladas ao evento correspondente

        System.out.println(geocodeParams.getLocalnames().toString());

        Evento eventoSalvo = eventoService.saveEvento(novoEvento);

        // Retornar o evento salvo e o status 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoSalvo);
    }


    @PutMapping("/{id}") // Mapeia para eventos/{id} com método PUT
    public ResponseEntity<Evento> updateEvento(@PathVariable Integer id, @RequestBody Evento eventoAtualizado) {
        // Verifique se o evento a ser atualizado existe
        EventoDTO evento = eventoService.findEventoById(id);
        if (evento == null) {
            return ResponseEntity.notFound().build(); // Retorna status 404 Not Found se o evento não existe
        }

        // Verificar se é valido IMPLEMENTAR
        if (eventoAtualizado == null) {
            return ResponseEntity.badRequest().build(); // Retorna status de BadRequest se o evento for inválido
        }

        Evento eventoAtualizadoNoBanco = eventoService.saveEvento(eventoAtualizado);

        // Evento atualizado e o status 200 OK
        return ResponseEntity.ok(eventoAtualizadoNoBanco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Integer id) {


        EventoDTO evento = eventoService.findEventoById(id);
        if (evento == null) {
            return ResponseEntity.notFound().build(); // Retorna status 404 Not Found se o evento não existe
        }


        // Exclua todas as tarefas associadas a este evento
        tarefaService.deleteAllTarefasByEvento(evento);

        eventoService.delete(id);

        // Retorne o status 204 No Content (sem conteúdo) para indicar a exclusão bem-sucedida
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @RequestMapping("/tarefas")
    public String getTarefa(Model model) {

        List<TarefaDTO> tarefas = tarefaService.findAllTarefas();
        model.addAttribute("tarefas", tarefas);

        return "logado"; // A confirmar
    }
    @PostMapping("/tarefas/{id}")
    public ResponseEntity<Tarefa> createTarefa(@PathVariable int idEvento, @RequestBody Tarefa novaTarefa) {

        // Verificar se é válido
        if (novaTarefa == null) {
            return ResponseEntity.badRequest().build();
        }

        EventoDTO evento = eventoService.findEventoById(idEvento);
        if (evento == null) {
            return ResponseEntity.notFound().build(); // Retorna status 404 Not Found se a tarefa não existe
        }

        Tarefa tarefaSalva = tarefaService.saveTarefa(novaTarefa);

        // Retornar a tarefa salva e o status 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaSalva);
    }

    @PutMapping("/tarefas/{id}") // Mapeia para tarefas/{id} com método PUT
    public ResponseEntity<Tarefa> updateTarefa(@PathVariable Integer id, @RequestBody Tarefa tarefaAtualizada) {

        // Verifique se a tarefa a ser atualizada existe
        TarefaDTO tarefa = tarefaService.findTarefaById(id);
        if (tarefa == null) {
            return ResponseEntity.notFound().build(); // Retorna status 404 Not Found se a tarefa não existe
        }

        // Verificar se é valido IMPLEMENTAR
        if (tarefaAtualizada == null) {
            return ResponseEntity.badRequest().build(); // Retorna status de BadRequest se a tarefa for inválida
        }

        Tarefa tarefaAtualizadaNoBanco = tarefaService.saveTarefa(tarefaAtualizada);

        // Tarefa atualizada e o status 200 OK
        return ResponseEntity.ok(tarefaAtualizadaNoBanco);
    }
    @DeleteMapping("/tarefas/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable Integer id) {

        TarefaDTO tarefa = tarefaService.findTarefaById(id);
        if (tarefa == null) {
            return ResponseEntity.notFound().build(); // Retorna status 404 Not Found
        }

        tarefaService.deleteTarefa(id);

        // Retorne o status 204 No Content (sem conteúdo) para indicar a exclusão bem-sucedida
        return ResponseEntity.noContent().build();
    }
}