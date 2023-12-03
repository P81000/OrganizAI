package com.organizai.app.controller;

import java.util.List;

import com.organizai.app.model.etiqueta.Etiqueta;
import com.organizai.app.model.etiqueta.EtiquetaDTO;
import com.organizai.app.model.etiqueta.service.EtiquetaService;
import com.organizai.app.model.evento.service.EventoService;
import com.organizai.app.model.usuario.Usuario;
import com.organizai.app.model.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/etiquetas")
public class EtiquetaController {

    private final EtiquetaService etiquetaService;
    private final EventoService eventoService;
    private final UsuarioService usuarioService;

    @Autowired
    public EtiquetaController(EtiquetaService etiquetaService, EventoService eventoService, UsuarioService usuarioService) {
        this.etiquetaService = etiquetaService;
        this.eventoService = eventoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String getEtiqueta(Model model) {

        List<EtiquetaDTO> Etiquetas = etiquetaService.findAllEtiquetas();
        model.addAttribute("etiquetas", Etiquetas);

        return "logado";
    }

    @PostMapping("/{email}")
    public ResponseEntity<Etiqueta> createEtiqueta(@PathVariable String email, @RequestBody Etiqueta novaEtiqueta) {

        // Verificar se é válido
        if (novaEtiqueta == null) {
            return ResponseEntity.badRequest().build();
        }
        //System.out.println("etiqueta: " + novaEtiqueta.getCorpo());

        Usuario usuario = usuarioService.findByEmail(email);
        if (usuario == null) {
            return ResponseEntity.notFound().build(); // Retorna status 404 Not Found se o usuário não existe
        }

        Etiqueta etiquetaSalva = etiquetaService.saveEtiqueta(novaEtiqueta);

        // Retornar a Etiqueta salva e o status 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(etiquetaSalva);
    }


    @PutMapping("/{id}") // Mapeia para eventos/{id} com método PUT
    public ResponseEntity<Etiqueta> updateEtiqueta(@PathVariable Integer id, @RequestBody Etiqueta etiquetaAtualizada) {
        // Verifique se a etiqueta a ser atualizada existe
        EtiquetaDTO etiqueta = etiquetaService.findEtiquetaById(id);
        if (etiqueta == null) {
            return ResponseEntity.notFound().build(); // Retorna status 404 Not Found se a etiqueta não existe
        }

        Etiqueta etiquetaAtualizadaNoBanco = etiquetaService.saveEtiqueta(etiquetaAtualizada);

        // Etiqueta atualizada e o status 200 OK
        return ResponseEntity.ok(etiquetaAtualizadaNoBanco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtiqueta(@PathVariable Integer id) {


        EtiquetaDTO etiqueta = etiquetaService.findEtiquetaById(id);
        if (etiqueta == null) {
            return ResponseEntity.notFound().build(); // Retorna status 404 Not Found se a Etiqueta não existe
        }

        etiquetaService.deleteEtiqueta(id);

        // Retorne o status 204 No Content (sem conteúdo) para indicar a exclusão bem-sucedida
        return ResponseEntity.noContent().build();
    }
}