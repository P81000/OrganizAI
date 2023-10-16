package com.organizai.app.controller;

import java.util.List;

import com.organizai.app.model.tarefa.Tarefa;
import com.organizai.app.model.tarefa.TarefaDTO;
import com.organizai.app.model.tarefa.service.TarefaService;
import com.organizai.app.model.evento.EventoDTO;
import com.organizai.app.model.evento.service.EventoService;
import com.organizai.app.model.usuario.Usuario;
import com.organizai.app.model.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public EventoController(EventoService eventoService, UsuarioService usuarioService, TarefaService tarefaService) {
        this.eventoService = eventoService;
        this.usuarioService = usuarioService;
        this.tarefaService = tarefaService;
    }

    @GetMapping
    public String getEvento(Model model) {

        List<EventoDTO> Eventos = eventoService.findAllEventos();
        model.addAttribute("eventos", Eventos);

        return "logado";
    }

    @PostMapping("/{email}")
    public ResponseEntity<Evento> createEvento(@PathVariable String email, @RequestBody Evento novoEvento) {

        // Verificar se é válido
        if (novoEvento == null) {
            return ResponseEntity.badRequest().build();
        }
        //System.out.println("evento: " + novoEvento.getCorpo());

        Usuario usuario = usuarioService.findByEmail(email);
        if (usuario == null) {
            return ResponseEntity.notFound().build(); // Retorna status 404 Not Found se o usuário não existe
        }

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