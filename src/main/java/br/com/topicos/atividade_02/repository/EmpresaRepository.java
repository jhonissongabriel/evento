package br.com.topicos.atividade_02.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.topicos.atividade_02.model.Empresa;

public interface EmpresaRepository extends CrudRepository<Empresa, Long> {
	
	// FIND BY
	
	public Empresa findByNomeEmpresa(String nomeEmpresa);
	
	
	// LISTA
	
	public List<Empresa> findAll();
	
	public List<Empresa> findByNomeEmpresaContains(String nomeEmpresa);
	
	public List<Empresa> findTop10ByNomeEmpresaContains(String nomeEmpresa);
		
	public List<Empresa> findLast5ByNomeEmpresaContains(String nomeEmpresa);
		
		
	// Query
	
	@Query("select e from Empresa e where e.nomeEmpresa like %?1%")
	public List<Empresa> buscaNomeEmpresa(String nomeEmpresa);
		

}
