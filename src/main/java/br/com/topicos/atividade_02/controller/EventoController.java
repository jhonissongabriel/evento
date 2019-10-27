package br.com.topicos.atividade_02.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.topicos.atividade_02.model.Empresa;
import br.com.topicos.atividade_02.model.Evento;
import br.com.topicos.atividade_02.service.EventoService;
import br.com.topicos.atividade_02.view.View;

@RestController
@RequestMapping(value = "/evento")
public class EventoController {

	@Autowired
	private EventoService eventoService;
	
	public void setEventoService(EventoService eventoService) {
		this.eventoService = eventoService;
	}


	// Pesquisar Nome Evento
	@RequestMapping(value = "/get/nome/{nome}")
	@JsonView(View.EventoBasico.class)
	public ResponseEntity<Collection<Evento>> pesquisarNomeEvento(@PathVariable("nome") String nome){
		List<Evento> eventos = eventoService.buscarNomeEvento(nome);
		if(eventos == null) {
			return new ResponseEntity<Collection<Evento>>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Collection<Evento>>(eventos, HttpStatus.OK);
		}
	}
	
	
	// Pesquisar Empresa
	@RequestMapping(value = "/get/empresa/{empresa}")
	@JsonView(View.EventoIntermediario.class)
	public ResponseEntity<Empresa> pesquisarEmpresaEvento(@PathVariable("empresa") Empresa empresa){
		Empresa empresaEvento = eventoService.buscarEmpresaEvento(empresa);
		if(empresaEvento == null) {
			return new ResponseEntity<Empresa>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Empresa>(empresaEvento, HttpStatus.OK);
		}
	}
	
	// Pesquisa por Id
	@RequestMapping(value = "/getById")
	@JsonView(View.EventoAvancado.class)
	public ResponseEntity<Evento> get(@RequestParam(value = "id", defaultValue = "1") Long id){
		Optional<Evento> evento =  eventoService.buscarIdEvento(id);
		if(evento == null) {
			return new ResponseEntity<Evento>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Evento>(HttpStatus.OK);

		}
	}
	
	// Buscar todas as Eventos
	@RequestMapping(value = "/getAll")
	@JsonView(View.EventoAvancado.class)
	public ResponseEntity<Collection<Evento>> getAll(){
		List<Evento> eventos = eventoService.todos();
		if(eventos == null) {
			return new ResponseEntity<Collection<Evento>>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Collection<Evento>>(eventos, HttpStatus.OK);
		}
	}
	
	
	// Cadastrar Evento
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.EventoAvancado.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Evento> save (@RequestBody Evento evento, HttpServletRequest request, HttpServletResponse response) {
		evento = eventoService.salvar(evento);
		if((evento.getNomeEvento().length() > 100) || (evento.getData().length() > 10) || (evento.getHora().length() > 5) || (evento.getLocal().length() > 100)) {
			// NÃO SEI SE ESTÁ CERTO ESTE STATUS
			return new ResponseEntity<Evento> (HttpStatus.URI_TOO_LONG);
		}else {
			if(evento.getNomeEvento().isEmpty() || evento.getData().isEmpty() || evento.getHora().isEmpty()) {
				// 			// NÃO SEI SE ESTÁ CERTO ESTE STATUS
				return new ResponseEntity<Evento>(HttpStatus.EXPECTATION_FAILED);
			}else {
				response.addHeader("Location", request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/evento/getById?id=" + evento.getId());
				return new ResponseEntity<Evento>(HttpStatus.CREATED);
			}
		}
	}
	
}

