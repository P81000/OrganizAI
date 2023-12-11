package com.organizai.app.controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.organizai.app.api.OpenWeatherApi;
import com.organizai.app.model.Geocode.Geocode;
import com.organizai.app.model.tarefa.Tarefa;
import com.organizai.app.model.tarefa.TarefaDTO;
import com.organizai.app.model.tarefa.service.TarefaService;
import com.organizai.app.model.evento.EventoDTO;
import com.organizai.app.model.evento.service.EventoService;
import com.organizai.app.model.usuario.Usuario;
import com.organizai.app.model.usuario.service.UsuarioService;
import com.organizai.app.model.weather.WeatherApiResponse;
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
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;
    private final UsuarioService usuarioService;
    private final TarefaService tarefaService;
    private final WeatherService weatherService;
    @Value("${weather.api.key}")
    private String _geocodeApiKey;

    private OpenWeatherApi apiManager;

    @Autowired
    public EventoController(EventoService eventoService, UsuarioService usuarioService, TarefaService tarefaService, WeatherService weatherService) {
        this.eventoService = eventoService;
        this.usuarioService = usuarioService;
        this.tarefaService = tarefaService;
        this.weatherService = weatherService;
    }

    @GetMapping
    public ResponseEntity<List<EventoDTO>> getEvento() {
        List<EventoDTO> Eventos = eventoService.findAllEventos();
        System.out.println(Eventos);
        return ResponseEntity.ok(Eventos);
    }

    @PostMapping("/criar")
    public ResponseEntity<Evento> createEvento(@RequestBody Evento novoEvento) {
        if (novoEvento == null) {
            return ResponseEntity.badRequest().build();
        }

//        Usuario usuario = usuarioService.findByEmail(email);
//        if (usuario == null) {
//            return ResponseEntity.notFound().build();
//        }
//        novoEvento.set_usuario(usuario);

        WeatherInfo weatherInfo = null;
        if(!novoEvento.getCidade().isEmpty()){
            this.apiManager = new OpenWeatherApi(_geocodeApiKey);
            Geocode geocodeParams = apiManager.GetGeocodeCoordinates(novoEvento.getCidade(), novoEvento.getEstado());
            WeatherApiResponse weatherApiResponse = apiManager.GetOpenWeather5DayForecast(geocodeParams.getLat(), geocodeParams.getLon());
            weatherInfo = weatherService.processAndSaveWeatherInfo(weatherApiResponse, novoEvento);

            System.out.println("Country: " + geocodeParams.getCountry());
        }

        if(weatherInfo != null){
            weatherInfo = weatherService.SaveWeatherInfo(weatherInfo);
        }
        
        Evento eventoSalvo = eventoService.saveEvento(novoEvento);

        return ResponseEntity.status(HttpStatus.CREATED).body(eventoSalvo);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable Integer id, @RequestBody Evento eventoAtualizado) {
        EventoDTO evento = eventoService.findEventoById(id);
        if (evento == null) {
            return ResponseEntity.notFound().build();
        }

        // Verificar se é valido IMPLEMENTAR
        if (eventoAtualizado == null) {
            return ResponseEntity.badRequest().build();
        }

        Evento eventoAtualizadoNoBanco = eventoService.saveEvento(eventoAtualizado);

        return ResponseEntity.ok(eventoAtualizadoNoBanco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Integer id) {

        System.out.println("cheguei1");

        EventoDTO evento = eventoService.findEventoById(id);
        if (evento == null) {
            return ResponseEntity.notFound().build();
        }

        System.out.println(id);

//        tarefaService.deleteAllTarefasByEvento(evento);

        eventoService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @RequestMapping("/tarefas")
    public String getTarefa(Model model) {

        List<TarefaDTO> tarefas = tarefaService.findAllTarefas();
        model.addAttribute("tarefas", tarefas);

        return "tarefa"; // A confirmar
    }
    @PostMapping("/tarefas/criar/{idEvento}")
    public ResponseEntity<Tarefa> createTarefa(@PathVariable Integer idEvento, @RequestBody Tarefa novaTarefa) {

        // Verificar se é válido
        if (novaTarefa == null) {
            return ResponseEntity.badRequest().build();
        }
       if (idEvento == null) {
            return ResponseEntity.badRequest().build(); // Retorna um status 400 Bad Request se o id for nulo ou "null"
        }
        //Integer id = Integer.parseInt(id_evento);
        EventoDTO evento = eventoService.findEventoById(idEvento);
        if (evento == null) {
            return ResponseEntity.notFound().build(); // Retorna status 404 Not Found se o evento não existe
        }

        //eventoService.addTarefaInEvento(idEvento, novaTarefa);
        novaTarefa.set_evento(eventoService.convertDTOToEntity(evento));

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
    @DeleteMapping("/tarefas/delete/{idTarefa}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable Integer idTarefa) {

        TarefaDTO tarefa = tarefaService.findTarefaById(idTarefa);
        if (tarefa == null) {
            return ResponseEntity.notFound().build(); // Retorna status 404 Not Found
        }

        tarefaService.deleteTarefa(idTarefa);

        // Retorne o status 204 No Content (sem conteúdo) para indicar a exclusão bem-sucedida
        return ResponseEntity.noContent().build();
    }
}