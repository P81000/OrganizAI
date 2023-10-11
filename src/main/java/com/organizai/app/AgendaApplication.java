package com.organizai.app;

import com.organizai.app.model.evento.EventoRepository;
import com.organizai.app.model.usuario.UsuarioRepository;
import com.organizai.app.model.tarefa.TarefaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AgendaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AgendaApplication.class, args);

		EventoRepository eventoRepository = context.getBean(EventoRepository.class);
		UsuarioRepository usuarioRepository = context.getBean(UsuarioRepository.class);
		TarefaRepository tarefaRepository = context.getBean(TarefaRepository.class);
	}
}
