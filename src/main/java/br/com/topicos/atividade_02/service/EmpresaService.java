package br.com.topicos.atividade_02.service;

import java.util.List;
import java.util.Optional;

import br.com.topicos.atividade_02.model.Empresa;
import br.com.topicos.atividade_02.model.Funcionario;

public interface EmpresaService {
	
	public Empresa incluirEmpresa(String nomeEmpresa);
	
	public void excluirEmpresa(String nomeEmpresa);
	
	public void incluirFuncionarioEmpresa(String nomeEmpresa, String identificacaoFuncionario);
	
	public void excluirFuncionarioEmpresa(String nomeEmpresa, String identificacaoFuncionario);
	
	public void alterarNomeEmpresa(String nomeEmpresaAntigo, String nomeEmpresaNovo);
	
	// Controller
	
	public List<Empresa> buscarNomeEmpresa(String nomeEmpresa);
			
	public Optional<Empresa> buscarIdEmpresa(Long id);
			
	public Funcionario buscarFuncionarioEmpresa(Funcionario funcionario);
			
	public List<Empresa> todos();
		
	public Empresa salvar(Empresa empresa);
	
}
