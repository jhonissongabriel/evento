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
import br.com.topicos.atividade_02.model.Funcionario;
import br.com.topicos.atividade_02.service.EmpresaService;
import br.com.topicos.atividade_02.view.View;

@RestController
@RequestMapping(value = "/empresa")
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;

	
	public void setEmpresaService(EmpresaService empresaService) {
		this.empresaService = empresaService;
	}


	// Pesquisar Nome Empresa
	@RequestMapping(value = "/get/nome/{nome}")
	@JsonView(View.EmpresaBasico.class)
	public ResponseEntity<Collection<Empresa>> pesquisarNomeEmpresa(@PathVariable("nome") String nome){
		List<Empresa> empresas = empresaService.buscarNomeEmpresa(nome);
		if(empresas == null) {
			return new ResponseEntity<Collection<Empresa>>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Collection<Empresa>>(empresas, HttpStatus.OK);
		}
	}
	
	
	// Pesquisar Funcionário
	@RequestMapping(value = "/get/funcionario/{funcionario}")
	@JsonView(View.EmpresaIntermediario.class)
	public ResponseEntity<Funcionario> pesquisarFuncionarioEmpresa(@PathVariable("funcionario") Funcionario funcionario){
		Funcionario funcionarioEmpresa = empresaService.buscarFuncionarioEmpresa(funcionario);
		if(funcionarioEmpresa == null) {
			return new ResponseEntity<Funcionario>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Funcionario>(funcionarioEmpresa, HttpStatus.OK);
		}
	}
	
	// Pesquisa por Id
	@RequestMapping(value = "/getById")
	@JsonView(View.EmpresaAvancado.class)
	public ResponseEntity<Empresa> get(@RequestParam(value = "id", defaultValue = "1") Long id){
		Optional<Empresa> empresa =  empresaService.buscarIdEmpresa(id);
		if(empresa == null) {
			return new ResponseEntity<Empresa>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Empresa>(HttpStatus.OK);
		}
	}
	
	// Buscar todas as Empresas
	@RequestMapping(value = "/getAll")
	@JsonView(View.EmpresaAvancado.class)
	public ResponseEntity<Collection<Empresa>> getAll(){
		List<Empresa> empresas = empresaService.todos();
		if(empresas == null) {
			return new ResponseEntity<Collection<Empresa>>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Collection<Empresa>>(empresas, HttpStatus.OK);
		}
	}
	
	
	// Cadastrar Empresa
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.EmpresaAvancado.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Empresa> save (@RequestBody Empresa empresa, HttpServletRequest request, HttpServletResponse response) {
		empresa = empresaService.salvar(empresa);
		if(empresa.getNomeEmpresa().length() > 100) {
			return new ResponseEntity<Empresa>(HttpStatus.URI_TOO_LONG); 
		}else {
			if(empresa.getNomeEmpresa().isEmpty()) {
				return new ResponseEntity<Empresa>(HttpStatus.EXPECTATION_FAILED);
			}else {
				response.addHeader("Location", request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/empresa/getById?id=" + empresa.getId());
				return new ResponseEntity<Empresa>(empresa, HttpStatus.OK);
			}
		}
	}
	
	
	
}
