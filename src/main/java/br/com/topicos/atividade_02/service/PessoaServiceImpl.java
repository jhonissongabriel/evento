package br.com.topicos.atividade_02.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.topicos.atividade_02.model.Pessoa;
import br.com.topicos.atividade_02.repository.PessoaRepository;

@Service("pessoaService")
public class PessoaServiceImpl implements PessoaService{

	@Autowired
	private PessoaRepository pessoaRepo;

	public void setPessoaRepo(PessoaRepository pessoaRepo) {
		this.pessoaRepo = pessoaRepo;
	}

	@Override
	@Transactional
	public Pessoa incluirPessoa(String nome, String rg, int idade, String telefone, String sexo) {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setRg(rg);
		pessoa.setIdade(idade);
		pessoa.setTelefone(telefone);
		pessoa.setSexo(sexo);
		pessoaRepo.save(pessoa);
		System.out.println("Pessoa cadastrada com sucesso!");
		return pessoa;
	}

	@Override
	@Transactional
	public void excluirPessoa(String rg) {
		Pessoa pessoa = pessoaRepo.findByRg(rg);
		if(pessoa == null){
			System.out.println("Pessoa não encontrada no sistema!");

		}else {
			pessoaRepo.delete(pessoa);
			System.out.println("Pessoa excluída com sucesso!");
		}
	}

	@Override
	@Transactional
	public void alterarNomePessoa(String nomeAntigo, String nomeNovo) {
		Pessoa pessoa = pessoaRepo.findByNome(nomeAntigo);
		if(pessoa == null) {
			System.out.println("Nome de pessoa não encontrado!");
		}else {
			pessoa.setNome(nomeNovo);
			pessoaRepo.save(pessoa);
			System.out.println("Nome da pessoa alterado com sucesso!");
		}
	}

	@Override
	@Transactional
	public void alterarRgPessoa(String rgAntigo, String rgNovo) {
		Pessoa pessoa = pessoaRepo.findByRg(rgAntigo);
		if(pessoa == null) {
			System.out.println("Rg não encontrado no banco de dados!");
		}else {
			pessoa.setRg(rgNovo);
			pessoaRepo.save(pessoa);
			System.out.println("Rg da pessoa alterado com sucesso!");
		}
	}

	@Override
	@Transactional
	public void alterarIdadePessoa(int idadeAntiga, int idadeNova) {
		Pessoa pessoa = pessoaRepo.findByIdade(idadeAntiga);
		if(pessoa == null) {
			System.out.println("Não foi encontrado nenhuma pessoa com esta idade!");
		}else {
			pessoa.setIdade(idadeNova);
			pessoaRepo.save(pessoa);
			System.out.println("Lista de idade das pessoas alterados com sucesso!");
		}
	}

	@Override
	@Transactional
	public void alterarTelefonePessoa(String telefoneAntigo, String telefoneNovo) {
		Pessoa pessoa = pessoaRepo.findByTelefone(telefoneAntigo);
		if(pessoa == null) {
			System.out.println("Telefone não encontrado no banco de dados!");
		}else {
			pessoa.setTelefone(telefoneNovo);
			pessoaRepo.save(pessoa);
			System.out.println("Telefone alterado com sucesso!");
		}
	}

	@Override
	@Transactional
	public void alterarSexoPessoa(String sexoAntigo, String sexoNovo) {
		Pessoa pessoa = pessoaRepo.findBySexo(sexoAntigo);
		if(pessoa == null) {
			System.out.println("Sexo não encontrado no banco de dados!");
		}else {
			pessoa.setSexo(sexoNovo);
			pessoaRepo.save(pessoa);
			System.out.println("Sexo da pessoa alterado com sucesso!");
		}
	}
	
	
	
	// CONTROLLER
	@Override
	public List<Pessoa> buscarNomePessoa(String nomePessoa) {
		List<Pessoa> pessoas = pessoaRepo.findByNomeContains(nomePessoa);
		if(pessoas == null) {
			System.out.println("Pessoa não encontrada");
			
		}else {
			pessoas.forEach(pessoa->System.out.println(pessoa));
		}
		return pessoas;
	}
	
	@Override
	public Optional<Pessoa> buscarIdPessoa(Long id) {
		Optional<Pessoa> pessoa = pessoaRepo.findById(id);
		if(pessoa == null) {
			System.out.println("Id pessoa não encontrado");
		}else {
			System.out.println(pessoa);
		}
		return pessoa;
	}
	
	@Override
	public Pessoa buscarRgPessoa(String rg) {
		Pessoa pessoa = pessoaRepo.findByRg(rg);
		if(pessoa == null) {
			System.out.println("Rg não encontrado.");
		}else {
			System.out.println(pessoa);
		}
		return pessoa;
	}
	
	
	@Override
	public List<Pessoa> todos() {
		List<Pessoa> pessoas = pessoaRepo.findAll();
		return pessoas;
	}
	
	
	@Override
	public Pessoa salvar(Pessoa pessoa) {
		if(!pessoa.getNome().isEmpty()) {
			System.out.println("Pessoa salvo com sucesso!");
			return pessoaRepo.save(pessoa);
		}else {
			System.out.println("Não foi possível salvar pessoa!");
			return null;
		}
		
	}
	
	
}
