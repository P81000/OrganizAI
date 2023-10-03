package com.organizai.app.controller;

import java.util.List;

import com.organizai.app.usuario.Usuario;
import com.organizai.app.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.organizai.app.evento.EventoRepository;

import com.organizai.app.evento.Evento;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping
    public String getEvento(Model model) {

        //Eventos list
        //Aqui vamos implementar as sessões e achar o usuário logado e assim buscar os eventos dele,
        // por hr serão todos os eventos mesmo de exemplo
        List<Evento> Eventos = eventoRepository.findAll();
        model.addAttribute("eventos", Eventos);

        return "logado";
    }

    @Autowired
    private UsuarioRepository usuarioRepository; // Injeta o UsuarioRepository

    /*
    @PostMapping

      public ResponseEntity<Evento> createEvento(@RequestBody Evento novoEvento) {
            // Verificar se é valido IMPLEMENTAR
            if (novoEvento == null) {
                  return ResponseEntity.badRequest().build(); // Retorna status de BadRequest se o evento for inválido
            }
            // Salve o novo evento no banco de dados
            Evento eventoSalvo = eventoRepository.save(novoEvento);

            // Retorne o evento salvo e o status 201 Created
            return ResponseEntity.status(HttpStatus.CREATED).body(eventoSalvo);
      }*/
    @PostMapping("/{email}")
    public ResponseEntity<Evento> createEvento(@PathVariable String email, @RequestBody Evento novoEvento) {

        // Verificar se é válido
        if (novoEvento == null) {
            return ResponseEntity.badRequest().build();
        }
        //System.out.println("evento: " + novoEvento.getCorpo());

        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            return ResponseEntity.notFound().build(); // Retorna status 404 Not Found se o usuário não existe
        }

        usuario.setEventos(novoEvento);
        novoEvento.set_usuario(usuario);
        //System.out.println("evento: " + novoEvento.getUsuario());

        Evento eventoSalvo = eventoRepository.save(novoEvento);
        //usuarioRepository.save()

        // Retornar o evento salvo e o status 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoSalvo);
    }


    @PutMapping("/{id}") // Mapeia para eventos/{id} com método PUT
    public ResponseEntity<Evento> updateEvento(@PathVariable Integer id, @RequestBody Evento eventoAtualizado) {
        // Verifique se o evento a ser atualizado existe
        if (!eventoRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Retorna status 404 Not Found se o evento não existe
        }

        // Verificar se é valido IMPLEMENTAR
        if (eventoAtualizado == null) {
            return ResponseEntity.badRequest().build(); // Retorna status de BadRequest se o evento for inválido
        }

        Evento eventoAtualizadoNoBanco = eventoRepository.save(eventoAtualizado);

        // Evento atualizado e o status 200 OK
        return ResponseEntity.ok(eventoAtualizadoNoBanco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Integer id) {

        if (!eventoRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Retorna status 404 Not Found
        }

        eventoRepository.deleteById(id);

        // Retorne o status 204 No Content (sem conteúdo) para indicar a exclusão bem-sucedida
        return ResponseEntity.noContent().build();
    }

}
