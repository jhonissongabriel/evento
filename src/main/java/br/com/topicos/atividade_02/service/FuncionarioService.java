package br.com.topicos.atividade_02.service;

import java.util.List;
import java.util.Optional;

import br.com.topicos.atividade_02.model.Funcionario;

public interface FuncionarioService {
	
	public Funcionario incluirFuncionario(String cargo, String identificacao, String nomePessoa);
	
	public void excluirFuncionario(String identificacao);
	
	public void alterarCargoFuncionario(String cargoAntigo, String cargoNovo);
	
	public void excluirCargoFuncionario(String cargo);
	
	public void alterarIdentificacaoFuncionario(String identificacaoAntiga, String identificacaoNova);
	
	public void excluirIdentificacaoFuncionario(String identificacao);
	
	// Controller
	
	public Funcionario buscarIdentificacaoFuncionario(String identificacao);
		
	public Optional<Funcionario> buscarIdFuncionario(Long id);
		
	public List<Funcionario> buscarCargoFuncionario(String cargo);
		
	public List<Funcionario> todos();
	
	public Funcionario salvar(Funcionario funcionario);

}
