package com.organizai.app.controller;
import com.organizai.app.model.LoginRequest; // Verifique o pacote real onde a classe está definida

import com.organizai.app.usuario.UsuarioRepository;
import com.organizai.app.usuario.Usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//VERIFICAR SE O EMAIL É VALIDO, E SE A SENHA POSSUI TODOS OS REQUISITOS;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository _usuarioRepository;

    @GetMapping
    public List<Usuario> getUsuarios(){
        return _usuarioRepository.findAll();
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){
        if(usuario == null) return ResponseEntity.badRequest().build();

        // Verifique se o email já está cadastrado
        Usuario usuarioExistente = _usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioExistente != null) {
            // O email já está em uso, retorne uma resposta de erro
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Você pode personalizar a resposta de erro conforme necessário
        }

        // Se o email não existe, continue com o cadastro
        Usuario novoUsuario = _usuarioRepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }



    private static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);
        byte[] hashedBytes = digest.digest(password.getBytes());

        // Converte o array de bytes em uma representação hexadecimal
        StringBuilder builder = new StringBuilder();
        for (byte b : hashedBytes) {
            builder.append(String.format("%02x", b));
        }

        return builder.toString();
    }

    public boolean validatePassword(String candidatePassword, String storedPassword, byte[] salt) throws NoSuchAlgorithmException {
        String hashedCandidatePassword = hashPassword(candidatePassword, salt);
        return hashedCandidatePassword.equals(storedPassword);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario){
        if(!_usuarioRepository.existsById(id)) return ResponseEntity.notFound().build();

        if(usuario == null) return ResponseEntity.badRequest().build();

        usuario.setId_usuario(id);

        var updatedUsuario = _usuarioRepository.save(usuario);

        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable Integer id){
        if(!_usuarioRepository.existsById(id)) return ResponseEntity.notFound().build();

        _usuarioRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws NoSuchAlgorithmException {
        // Recupere o usuário com base no nome de usuário (ou email) fornecido no login
        Usuario usuario = _usuarioRepository.findByEmail(loginRequest.getEmail());

          //System.out.println(usuario.getPassword());
       // System.out.println(loginRequest.getPassword());
        // System.out.println(loginRequest.getEmail());


        if (usuario == null) {

            // Usuário não encontrado, login falhou
            System.out.println("Usuário não encontrado.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado.");

        }

        try {


            // Use a função de hash para calcular o hash da senha inserida no login

            String hashedPassword = hashPassword(loginRequest.getPassword(), usuario.getSalt());

            /* System.out.println(Arrays.toString(usuario.getSalt()));
            System.out.println(usuario.getPassword());

            System.out.println(hashedPassword);*/

            // Compare o hash calculado com o hash da senha armazenada no banco de dados
            if (hashedPassword.equals(usuario.getPassword())) {
                // Senha válida, login bem-sucedido
                System.out.println("Login bem-sucedido");
                System.out.println("Eventos: "+ usuario.getEventos());

                return ResponseEntity.ok("Login bem-sucedido. " + usuario.getEmail() +usuario.getEventos());

            } else {
                // Senha inválida, login falhou
                //System.out.println(usuario.getPassword());
                System.out.println("Senha inválida.");
                System.out.println(loginRequest.getPassword());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha inválida.");
            }
        } catch (NoSuchAlgorithmException e) {
            // Tratar exceção de algoritmo de hash não suportado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno.");
        }
    }
//    @GetMapping("/login")
//    public String loginPage() {
//        return "login"; // Retorna o nome do arquivo HTML (sem a extensão)
//    }


}
