package br.com.topicos.atividade_02.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.topicos.atividade_02.model.Evento;

public interface EventoRepository extends CrudRepository<Evento, Long>{
	
	// FIND BY
	
	public Evento findByNomeEvento(String nome);
	
	public Evento findByData(String data);
	
	public Evento findByHora(String hora);
	
	public Evento findByLocal(String local);
	
	
	// LISTA
	
	public List<Evento> findAll();
	
	public List<Evento> findByNomeEventoContains(String nome);
		
	public List<Evento> findTop10ByDataContains(String data);
	
	public List<Evento> findByDataAndHora(String data, String hora); //testar esse para ver se funciona
		
	public List<Evento> findByLocalContains(String local);
		
		
	// Query
	
	@Query("select e from Evento e where e.nomeEvento like %?1%")
	public List<Evento> buscaNomeEvento(String nome);
		
	@Query("select e from Evento e where e.data like %?1%")
	public List<Evento> buscaDataEvento(String data);
		
	@Query("select e from Evento e where e.hora like %?1%")
	public List<Evento> buscaHoraEvento(String hora);
		
	@Query("select e from Evento e where e.local like %?1%")
	public List<Evento> buscaLocalEvento(String local);
	
}
