package br.com.topicos.atividade_02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.topicos.atividade_02.service.EmpresaService;
import br.com.topicos.atividade_02.service.EventoService;
import br.com.topicos.atividade_02.service.FuncionarioService;
import br.com.topicos.atividade_02.service.PessoaService;

@SpringBootApplication
public class Atividade02Application implements CommandLineRunner {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private EventoService eventoService;

	public static void main(String[] args) {
		SpringApplication.run(Atividade02Application.class, args);
	}

	public void setEventoService(EventoService eventoService) {
		this.eventoService = eventoService;
	}

	public void setPessoaService(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	public void setOrganizacaoService(EmpresaService empresaService) {
		this.empresaService = empresaService;
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
