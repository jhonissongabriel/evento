package br.com.topicos.atividade_02.service;

import java.util.List;
import java.util.Optional;

import br.com.topicos.atividade_02.model.Empresa;
import br.com.topicos.atividade_02.model.Evento;

public interface EventoService {
	//nomeEmpresa representa a empresa que está organizando o evento
	public Evento incluirEvento(String nomeEvento, String data, String hora, String local, String nomeEmpresa); 
	
	public void excluirEvento(String nomeEvento);
	
	public void alterarNomeEvento(String nomeAntigo, String nomeNovo);
	
	public void alterarDataEvento(String dataAntiga, String dataNova);
	
	public void alterarHoraEvento(String horaAntiga, String horaNova);
	
	public void alterarLocalEvento(String localAntigo, String localNovo);
	
	// Controller
	
	public List<Evento> buscarNomeEvento(String nomeEvento);
				
	public Optional<Evento> buscarIdEvento(Long id);
				
	public Empresa buscarEmpresaEvento(Empresa empresa);
				
	public List<Evento> todos();
			
	public Evento salvar(Evento evento);

}
