package com.organizai.app;

import com.organizai.app.evento.Evento;
import com.organizai.app.evento.EventoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AgendaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AgendaApplication.class, args);


		EventoRepository eventoRepository = context.getBean(EventoRepository.class);


	}
}
