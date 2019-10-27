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

import br.com.topicos.atividade_02.model.Pessoa;
import br.com.topicos.atividade_02.service.PessoaService;
import br.com.topicos.atividade_02.view.View;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
		
	public void setPessoaService(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}


	// Pesquisar Pessoa
	@RequestMapping(value = "/get/nome/{nome}", method = RequestMethod.GET)
	@JsonView(View.PessoaBasico.class)
	public ResponseEntity<Collection<Pessoa>> pesquisarNomePessoa(@PathVariable("nome") String nome){
		List<Pessoa> pessoas = pessoaService.buscarNomePessoa(nome);
		if(pessoas == null) {
			return new ResponseEntity<Collection<Pessoa>>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Collection<Pessoa>>(pessoas, HttpStatus.OK);
		}
	}
	
	// Pesquisar RG
	@RequestMapping(value = "/get/rg/{rg}", method = RequestMethod.GET)
	@JsonView(View.PessoaIntermediario.class)
	public ResponseEntity<Pessoa> pesquisarRgPessoa(@PathVariable("rg") String rg){
		Pessoa pessoa = pessoaService.buscarRgPessoa(rg);
		if(pessoa == null) {
			return new ResponseEntity<Pessoa>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
		}
	}
	
	// Pesquisa por Id // ESTOU UTILIZANDO INT NO LUGAR DE LONG EM ID
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	@JsonView(View.PessoaAvancado.class)
	public ResponseEntity<Optional<Pessoa>> get(@RequestParam(value = "id", defaultValue = "1") Long id){
		Optional<Pessoa> pessoa =  pessoaService.buscarIdPessoa(id);
		if(pessoa == null) {
			return new ResponseEntity<Optional<Pessoa>>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Optional<Pessoa>>(pessoa,HttpStatus.OK);
		}
	}
	
	// Buscar todas as Pessoas
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@JsonView(View.PessoaAvancado.class)
	public ResponseEntity<Collection<Pessoa>> getAll(){
		List<Pessoa> pessoas = pessoaService.todos();
		if(pessoas == null) {
			return new ResponseEntity<Collection<Pessoa>>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Collection<Pessoa>>(pessoas, HttpStatus.OK);
		}
	}
	
	
	// Cadastrar Pessoa
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)//remover o produces
	@JsonView(View.PessoaAvancado.class)
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pessoa> save (@RequestBody Pessoa pessoa, HttpServletRequest request, HttpServletResponse response) {
		pessoa = pessoaService.salvar(pessoa);
		// COMPARAÇÃO DE TAMANHO
		if((pessoa.getNome().length() > 100) || (pessoa.getRg().length() > 10) || (pessoa.getTelefone().length() > 11) || (pessoa.getSexo().length() > 1)) {
			// NÃO SEI SE ESTE STATUS ESTÁ CERTO
			return new ResponseEntity<Pessoa>(HttpStatus.URI_TOO_LONG); // erro 414
		}else {
			if(pessoa.getNome().isEmpty() || pessoa.getRg().isEmpty()) {
				// NÃO SEI SE ESTE STATUS ESTÁ CERTO
				return new ResponseEntity<Pessoa>(HttpStatus.EXPECTATION_FAILED); // erro 417
			}else {
				//if(pessoa.getNome().matches("[a-zA-Z\s]+")) {
				System.out.println("deixar assim por enquanto");
				response.addHeader("Location", request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/pessoa/getById?id=" + pessoa.getId());
				return new ResponseEntity<Pessoa> (pessoa, HttpStatus.CREATED);
			}
		}
	}
		
}
