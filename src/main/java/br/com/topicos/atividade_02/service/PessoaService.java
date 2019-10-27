package br.com.topicos.atividade_02.service;

import java.util.List;
import java.util.Optional;

import br.com.topicos.atividade_02.model.Pessoa;

public interface PessoaService {
	
	public Pessoa incluirPessoa(String nome, String rg, int idade, String telefone, String sexo);
	
	public void excluirPessoa(String rg);
	
	public void alterarNomePessoa(String nomeAntigo, String nomeNovo);
	
	public void alterarRgPessoa(String rgAntigo, String rgNovo);
	
	public void alterarIdadePessoa(int idadeAntiga, int idadeNova);
	
	public void alterarTelefonePessoa(String telefoneAntigo, String telefoneNovo);
	
	public void alterarSexoPessoa(String sexoAntigo, String sexoNovo);
	
	// Controller
	
	public List<Pessoa> buscarNomePessoa(String nomePessoa);
	
	public Optional<Pessoa> buscarIdPessoa(Long id);
	
	public Pessoa buscarRgPessoa(String rg);
	
	public List<Pessoa> todos();
	
	public Pessoa salvar(Pessoa pessoa);

}
