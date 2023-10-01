package com.organizai.app.controller;

import java.util.List;
import java.util.Optional;

import com.organizai.app.usuario.Usuario;
import com.organizai.app.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.organizai.app.evento.EventoRepository;

import com.organizai.app.evento.Evento;

@RestController
@RequestMapping("/eventos")

public class EventoController {

      @Autowired
      private EventoRepository eventoRepository;

      @GetMapping
      public List<Evento> getEvento() {

            //Eventos list
            List<Evento> Eventos = eventoRepository.findAll();
            return Eventos;
      }
    @Autowired
    private UsuarioRepository usuarioRepository; // Injete o UsuarioRepository

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

        System.out.println(email);
        if (novoEvento == null) {
            return ResponseEntity.badRequest().build();
        }
        Usuario usuario = usuarioRepository.findByEmail(email);
        // Verificar se o usuário existe
        if (usuario==null) {
            return ResponseEntity.notFound().build(); // Retorna status 404 Not Found se o usuário não existe
        }
              //  System.out.println(" usuario : "+ usuario);
             //   System.out.println("evento usuario antes: "+ usuario.getEventos());
               usuario.setEventos(novoEvento);
             //   System.out.println("Usuario: " +usuario.getUsername() +"evento:" +  usuario.getEventos());
               novoEvento.set_usuario(usuario);
        System.out.println("evento: "+ novoEvento.getUsuario() );
        //usuarioRepository.save(usuario);
        // Vincular o evento ao usuário

        // Salvar o novo evento no banco de dados
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

            eventoAtualizado.setId_evento(id);

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
