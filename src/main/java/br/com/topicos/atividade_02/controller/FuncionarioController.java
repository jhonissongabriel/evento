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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.topicos.atividade_02.model.Funcionario;
import br.com.topicos.atividade_02.service.FuncionarioService;
import br.com.topicos.atividade_02.view.View;

@RestController
@RequestMapping(value = "/funcionario")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}


	// Pesquisar Cargo Funcionario
	@RequestMapping(value = "/get/cargo/{cargo}", method = RequestMethod.GET)
	@JsonView(View.FuncionarioBasico.class)
	public ResponseEntity<Collection<Funcionario>> pesquisarCargoFuncionario(@PathVariable("cargo") String cargo){
		List<Funcionario> funcionarios = funcionarioService.buscarCargoFuncionario(cargo);
		if(funcionarios == null) {
			return new ResponseEntity<Collection<Funcionario>>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Collection<Funcionario>>(funcionarios, HttpStatus.OK);
		}
	}
	
	
	// Pesquisar Identificação
	@RequestMapping(value = "/get/identificacao/{identificacao}", method = RequestMethod.GET)
	@JsonView(View.FuncionarioIntermediario.class)
	public ResponseEntity<Funcionario> pesquisarIdentificacaoFuncionario(@PathVariable("identificacao") String identificacao){
		Funcionario funcionario = funcionarioService.buscarIdentificacaoFuncionario(identificacao);
		if(funcionario == null) {
			return new ResponseEntity<Funcionario>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Funcionario>(funcionario, HttpStatus.OK);
		}
	}
	
	// Pesquisa por Id
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	@JsonView(View.FuncionarioAvancado.class)
	public ResponseEntity<Funcionario> get(@RequestParam(value = "id", defaultValue = "1") Long id){
		Optional<Funcionario> funcionario =  funcionarioService.buscarIdFuncionario(id);
		if(funcionario == null) {
			return new ResponseEntity<Funcionario>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Funcionario>(HttpStatus.OK);

		}
	}
	
	// Buscar todas as Funcionarios
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@JsonView(View.FuncionarioAvancado.class)
	public ResponseEntity<Collection<Funcionario>> getAll(){
		List<Funcionario> funcionarios = funcionarioService.todos();
		if(funcionarios == null) {
			return new ResponseEntity<Collection<Funcionario>>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Collection<Funcionario>>(funcionarios, HttpStatus.OK);
		}
	}
	
	
	// Cadastrar Funcionario
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.FuncionarioAvancado.class)
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Funcionario> save (@RequestBody Funcionario funcionario, HttpServletRequest request, HttpServletResponse response) {
		funcionario = funcionarioService.salvar(funcionario);
		if((funcionario.getCargo().length() > 50) || (funcionario.getIdentificacao().length() > 6)) {
			// NÃO SEI SE ESTÁ CERTO ESTE STATUS
			return new ResponseEntity<Funcionario>(HttpStatus.URI_TOO_LONG);
		}else {
			if(funcionario.getIdentificacao().isEmpty() || funcionario.getCargo().isEmpty()) {
				// NÃO SEI SE ESTÁ CERTO ESTE STATUS
				return new ResponseEntity<Funcionario>(HttpStatus.EXPECTATION_FAILED);
			}else {
				response.addHeader("Location", request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/funcionario/getById?id=" + funcionario.getId());
				return new ResponseEntity<Funcionario>(funcionario, HttpStatus.CREATED);
			}
		}
		
	}

}
