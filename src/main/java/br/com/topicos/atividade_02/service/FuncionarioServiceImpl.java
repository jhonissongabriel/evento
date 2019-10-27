package br.com.topicos.atividade_02.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.topicos.atividade_02.model.Funcionario;
import br.com.topicos.atividade_02.model.Pessoa;
import br.com.topicos.atividade_02.repository.FuncionarioRepository;
import br.com.topicos.atividade_02.repository.PessoaRepository;

@Service("funcionarioService")
public class FuncionarioServiceImpl implements FuncionarioService{

	@Autowired
	private FuncionarioRepository funcionarioRepo;
	
	@Autowired
	private PessoaRepository pessoaRepo;
	

	public void setFuncionarioRepo(FuncionarioRepository funcionarioRepo) {
		this.funcionarioRepo = funcionarioRepo;
	}

	public void setPessoaRepo(PessoaRepository pessoaRepo) {
		this.pessoaRepo = pessoaRepo;
	}


	@Override
	@Transactional
	public Funcionario incluirFuncionario(String cargo, String identificacao, String nomePessoa) {
		Pessoa pessoa = pessoaRepo.findByNome(nomePessoa);
		
		if(pessoa == null) {
			System.out.println("Esta pessoa que será funcionario não existe");
			return null;
			
		}else {
			Funcionario funcionario = new Funcionario();
			funcionario.setCargo(cargo);
			funcionario.setIdentificacao(identificacao);
			funcionario.setPessoa(pessoa);
			funcionarioRepo.save(funcionario);
			System.out.println("Funcionario cadastrado com sucesso!");
			return funcionario;
		}
	}

	@Override
	@Transactional
	public void excluirFuncionario(String identificacao) {
		Funcionario funcionario = funcionarioRepo.findByIdentificacao(identificacao);
		
		if(funcionario == null) {
			System.out.println("Esta identificação não existe!");
		}else {
			funcionarioRepo.delete(funcionario);
		}
	}

	@Override
	@Transactional
	public void alterarCargoFuncionario(String cargoAntigo, String cargoNovo) {
		List<Funcionario> funcionarios = funcionarioRepo.buscaCargoFuncionario(cargoAntigo);
		if(funcionarios == null) {
			System.out.println("Não existe este cargo no banco de dados!");
		}else {
			funcionarios.forEach(funcionario->funcionario.setCargo(cargoNovo));
			funcionarioRepo.saveAll(funcionarios);
			System.out.println("Cargo alterado com sucesso!");		}
	}

	@Override
	@Transactional
	public void excluirCargoFuncionario(String cargo) {
		List<Funcionario> funcionarios = funcionarioRepo.buscaCargoFuncionario(cargo);
		
		if(funcionarios == null) {
			System.out.println("Não existe este cargo no banco de dados!");
		}else {
			funcionarios.forEach(funcionario->funcionario.setCargo(null));
			funcionarioRepo.saveAll(funcionarios);
			System.out.println("Cargo excluido com sucesso!");
		}
		
	}

	@Override
	@Transactional
	public void alterarIdentificacaoFuncionario(String identificacaoAntiga, String identificacaoNova) {
		Funcionario funcionario = funcionarioRepo.findByIdentificacao(identificacaoAntiga);
		
		if(funcionario == null) {
			System.out.println("Esta identificação não existe!");
		}else {
			funcionario.setIdentificacao(identificacaoNova);
			funcionarioRepo.save(funcionario);
			System.out.println("Identificação alterada com sucesso!");
		}
	}

	@Override
	@Transactional
	public void excluirIdentificacaoFuncionario(String identificacao) {
		Funcionario funcionario = funcionarioRepo.findByIdentificacao(identificacao);
		
		if(funcionario == null) {
			System.out.println("Esta identificação não existe!");
		}else {
			funcionarioRepo.delete(funcionario);
			System.out.println("Identificação excluída com sucesso!");

		}
	}

	
	// CONTROLLER
	
	@Override
	public Funcionario buscarIdentificacaoFuncionario(String identificacao) {
		Funcionario funcionario = funcionarioRepo.buscaIdentificacaoFuncionario(identificacao);
		if(funcionario == null) {
			System.out.println("Este funcionário não existe!");
		}else {
			System.out.println(funcionario);
		}
		return funcionario;
	}

	@Override
	public Optional<Funcionario> buscarIdFuncionario(Long id) {
		Optional<Funcionario> funcionario = funcionarioRepo.findById(id);
		if(funcionario == null) {
			System.out.println("Funcionário não encontrado!");
		}else {
			System.out.println(funcionario);
		}
		return funcionario;
	}

	@Override
	public List<Funcionario> buscarCargoFuncionario(String cargo) {
		List<Funcionario> cargos = funcionarioRepo.findByCargoContains(cargo);
		if(cargos == null) {
			System.out.println("Este cargo não existe!");
		}else {
			System.out.println(cargos);
		}
		return cargos;
	}

	@Override
	public List<Funcionario> todos() {
		List<Funcionario> funcionarios = funcionarioRepo.findAll();
		return funcionarios;
	}

	@Override
	public Funcionario salvar(Funcionario funcionario) {
		if(!funcionario.getIdentificacao().isEmpty()) {
			System.out.println("Funcionário salvo!");
			return funcionarioRepo.save(funcionario);
		}else {
			return null;
		}
		
		
	}
	
	
	
}
