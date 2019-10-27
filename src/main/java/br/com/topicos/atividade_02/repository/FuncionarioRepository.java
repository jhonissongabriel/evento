package br.com.topicos.atividade_02.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.topicos.atividade_02.model.Funcionario;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Long>{
	
		// FIND BY
			
		public Funcionario findByCargo(String cargo);
		
		public Funcionario findByIdentificacao(String identificacao);
		
		public Funcionario findByPessoa(String nomePessoa);
		
		
		// LISTA
		
		public List<Funcionario> findAll();
		
		public List<Funcionario> findTop10ByCargoContains(String cargo);
			
		public List<Funcionario> findByCargoContains(String cargo);
		
		public List<Funcionario> findByIdentificacaoContains(String identificacao);
		
		public List<Funcionario> findByPessoaContains(String nomePessoa);
			
			
		// Query
		
		@Query("select f from Funcionario f where f.cargo like %?1%")
		public List<Funcionario> buscaCargoFuncionario(String cargo);
			
		@Query("select f from Funcionario f where f.identificacao like %?1%")
		public Funcionario buscaIdentificacaoFuncionario(String identificacao);
			

}
