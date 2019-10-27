package br.com.topicos.atividade_02;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.topicos.atividade_02.model.Empresa;
import br.com.topicos.atividade_02.model.Evento;
import br.com.topicos.atividade_02.model.Funcionario;
import br.com.topicos.atividade_02.model.Pessoa;
import br.com.topicos.atividade_02.repository.EmpresaRepository;
import br.com.topicos.atividade_02.repository.EventoRepository;
import br.com.topicos.atividade_02.repository.FuncionarioRepository;
import br.com.topicos.atividade_02.repository.PessoaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class Atividade02ApplicationTests {
	
	// PESSOA
	private static final String NOMEPESSOA = "Joao";
	private static final String NOMEPESSOAANTIGO = "Maria";
	private static final String NOMEPESSOANOVO = "Clara";
	private static final String RGPESSOA = "1111111111";
	private static final String RGPESSOAANTIGO = "2222222222";
	private static final String RGPESSOANOVO = "3333333333";
	private static final int IDADEPESSOA = 20;
	private static final int IDADEPESSOAANTIGO = 30;
	private static final int IDADEPESSOANOVO = 40;
	private static final String TELEFONEPESSOA = "99999999999";
	private static final String TELEFONEPESSOAANTIGO = "55555555555";
	private static final String TELEFONEPESSOANOVO = "00000000000";
	private static final String SEXOPESSOA = "M";
	private static final String SEXOPESSOAANTIGO = "S";
	private static final String SEXOPESSOANOVO = "M";
	
	// FUNCIONARIO
	private static final String CARGOFUNCIONARIO = "Seguranca";
	private static final String CARGOFUNCIONARIOANTIGO = "Piloto";
	private static final String CARGOFUNCIONARIONOVO = "Carpinteiro";
	private static final String IDENTIFICACAOFUNCIONARIO = "111111";
	private static final String IDENTIFICACAOFUNCIONARIOANTIGO = "222222";
	private static final String IDENTIFICACAOFUNCIONARIONOVO = "444444";

	// EMPRESA
	private static final String NOMEEMPRESA = "EMPRESA X";
	private static final String NOMEEMPRESAANTIGO = "EMPRESA Y";
	private static final String NOMEEMPRESANOVO = "EMPRESA Z";
	
	// EVENTO
	private static final String NOMEEVENTO = "FATEC TESTE";
	private static final String NOMEEVENTOANTIGO = "FATEC 1";
	private static final String NOMEEVENTONOVO = "FATEC 2";
	private static final String DATAEVENTO = "02/09/2019";
	private static final String DATAEVENTOANTIGO = "27/08/2019";
	private static final String DATAEVENTONOVO = "03/09/2019";
	private static final String HORAEVENTO = "12:30";
	private static final String HORAEVENTOANTIGO = "12:00";
	private static final String HORAEVENTONOVO = "23:55";
	private static final String LOCALEVENTO = "RUA A";
	private static final String LOCALEVENTOANTIGO = "RUA B";
	private static final String LOCALEVENTONOVO = "RUA C";
	
	
	@Autowired
	private PessoaRepository pessoaRepo;
	
	@Autowired
	private FuncionarioRepository funcionarioRepo;
	
	@Autowired
	private EmpresaRepository empresaRepo;
	
	@Autowired
	private EventoRepository eventoRepo;
	
	

	public void setPessoaRepo(PessoaRepository pessoaRepo) {
		this.pessoaRepo = pessoaRepo;
	}

	public void setFuncionarioRepo(FuncionarioRepository funcionarioRepo) {
		this.funcionarioRepo = funcionarioRepo;
	}

	public void setEmpresaRepo(EmpresaRepository empresaRepo) {
		this.empresaRepo = empresaRepo;
	}

	public void setEventoRepo(EventoRepository eventoRepo) {
		this.eventoRepo = eventoRepo;
	}


	
	
	// PESSOA
	@Test
	public void testIncluirPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(NOMEPESSOA);
		pessoa.setRg(RGPESSOA);
		pessoa.setIdade(IDADEPESSOA);
		pessoa.setTelefone(TELEFONEPESSOA);
		pessoa.setSexo(SEXOPESSOA);
		pessoaRepo.save(pessoa);
		assertTrue(pessoa.getId() != null);
	}
	
	@Test
	public void testBuscarPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(NOMEPESSOA);
		pessoa.setRg(RGPESSOA);
		pessoa.setIdade(IDADEPESSOA);
		pessoa.setTelefone(TELEFONEPESSOA);
		pessoa.setSexo(SEXOPESSOA);
		pessoaRepo.save(pessoa);
		System.out.println("Id Pessoa: "+pessoa.getId());
		
		Pessoa pessoaBusca = pessoaRepo.findByNome(NOMEPESSOA);
		System.out.println("Nome do Evento: "+pessoaBusca.getNome());
		System.out.println("ID: "+pessoaBusca.getId());
		
		assertTrue(pessoaBusca.getId() != null);
	}
	
	
	// FUNCIONARIO
	@Test
	public void testIncluirFuncionario() {
		Funcionario funcionario = new Funcionario();
		funcionario.setCargo(CARGOFUNCIONARIO);
		funcionario.setIdentificacao(IDENTIFICACAOFUNCIONARIO);
		// pessoa
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(NOMEPESSOA);
		pessoa.setRg(RGPESSOA);
		pessoa.setIdade(IDADEPESSOA);
		pessoa.setTelefone(TELEFONEPESSOA);
		pessoa.setSexo(SEXOPESSOA);
		
		funcionario.setPessoa(pessoa);
		funcionarioRepo.save(funcionario);
		assertTrue(funcionario.getId() != null);
	}
	
	// EMPRESA
	@Test
	public void testIncluirEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setNomeEmpresa(NOMEEMPRESA);
		
		// pessoa
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(NOMEPESSOA);
		pessoa.setRg(RGPESSOA);
		pessoa.setIdade(IDADEPESSOA);
		pessoa.setTelefone(TELEFONEPESSOA);
		pessoa.setSexo(SEXOPESSOA);
		
		//funcionario
		Funcionario funcionario = new Funcionario();
		funcionario.setCargo(CARGOFUNCIONARIO);
		funcionario.setIdentificacao(IDENTIFICACAOFUNCIONARIO);
		funcionario.setPessoa(pessoa);
		
		empresa.setFuncionario(funcionario);
		empresaRepo.save(empresa);
		assertTrue(empresa.getId() != null);
	}
	
	
	// EVENTO
	@Test
	public void testIncluirEvento() {
		Evento evento = new Evento();
		evento.setNomeEvento(NOMEEVENTO);
		evento.setData(DATAEVENTO);
		evento.setHora(HORAEVENTO);
		evento.setLocal(LOCALEVENTO);
		
		// pessoa
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(NOMEPESSOA);
		pessoa.setRg(RGPESSOA);
		pessoa.setIdade(IDADEPESSOA);
		pessoa.setTelefone(TELEFONEPESSOA);
		pessoa.setSexo(SEXOPESSOA);
		
		//funcionario
		Funcionario funcionario = new Funcionario();
		funcionario.setCargo(CARGOFUNCIONARIO);
		funcionario.setIdentificacao(IDENTIFICACAOFUNCIONARIO);
		funcionario.setPessoa(pessoa);
		
		//empresa
		Empresa empresa = new Empresa();
		empresa.setNomeEmpresa(NOMEEMPRESA);
		empresa.setFuncionario(funcionario);
		
		evento.setEmpresa(empresa);
		eventoRepo.save(evento);
		assertTrue(evento.getId() != null);
	}
	
	
	@Test
	public void testBuscarEvento() {
		Evento evento = new Evento();
		evento.setNomeEvento(NOMEEVENTO);
		evento.setData(DATAEVENTO);
		evento.setHora(HORAEVENTO);
		evento.setLocal(LOCALEVENTO);
		
		// pessoa
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(NOMEPESSOA);
		pessoa.setRg(RGPESSOA);
		pessoa.setIdade(IDADEPESSOA);
		pessoa.setTelefone(TELEFONEPESSOA);
		pessoa.setSexo(SEXOPESSOA);
		pessoaRepo.save(pessoa);
		
		//funcionario
		Funcionario funcionario = new Funcionario();
		funcionario.setCargo(CARGOFUNCIONARIO);
		funcionario.setIdentificacao(IDENTIFICACAOFUNCIONARIO);
		funcionario.setPessoa(pessoa);
		funcionarioRepo.save(funcionario);
		
		//empresa
		Empresa empresa = new Empresa();
		empresa.setNomeEmpresa(NOMEEMPRESA);
		empresa.setFuncionario(funcionario);
		empresaRepo.save(empresa);
		
		evento.setEmpresa(empresa);
		eventoRepo.save(evento);
		
		//vai achar o evento pois este está cadastrado
		Evento eventoBusca = eventoRepo.findByNomeEvento(NOMEEVENTO);
		System.out.println("Nome do Evento: "+eventoBusca.getNomeEvento());
		System.out.println("ID: "+eventoBusca.getId());

		assertTrue(eventoBusca.getId() != null);
	}
}
