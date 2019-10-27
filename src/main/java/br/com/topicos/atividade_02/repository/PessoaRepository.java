package br.com.topicos.atividade_02.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.topicos.atividade_02.model.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Long>{
	
	// FIND BY
	
	public Optional<Pessoa> findById(Long id);
		
	public Pessoa findByNome(String nome);
	
	public Pessoa findByRg(String rg);
	
	public Pessoa findByIdade(int idade);
	
	public Pessoa findByTelefone(String telefone);
	
	public Pessoa findBySexo(String sexo);
	
	
	// LISTA
	
	public List<Pessoa> findAll();
	
	public List<Pessoa> findByNomeContains(String nome);
	
	public List<Pessoa> findTop10ByRgContains(String rg);
	
	public List<Pessoa> findByIdadeGreaterThan(int idade);
	
	
	// Query
	
	@Query("select p from Pessoa p where p.nome like %?1%")
	public List<Pessoa> buscaNomePessoa(String nome);
	
	@Query("select p from Pessoa p where p.rg like %?1%")
	public List<Pessoa> buscaRgPessoa(String rg);
	
	@Query("select p from Pessoa p where p.idade like %?1%")
	public List<Pessoa> buscaIdadePessoa(int idade);
	
	@Query("select p from Pessoa p where p.telefone like %?1%")
	public List<Pessoa> buscaTelefonePessoa(String telefone);
	
	@Query("select p from Pessoa p where p.sexo like %?1%")
	public List<Pessoa> buscaSexoPessoa(Pessoa pessoa);
	
	
	
	
	
	
}
