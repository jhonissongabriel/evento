package br.com.topicos.atividade_02.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.topicos.atividade_02.model.Evento;
import br.com.topicos.atividade_02.model.Empresa;
import br.com.topicos.atividade_02.repository.EventoRepository;
import br.com.topicos.atividade_02.repository.EmpresaRepository;

@Service("eventoService")
public class EventoServiceImpl implements EventoService{
	
	
	@Autowired
	private EventoRepository eventoRepo;
	
	@Autowired
	private EmpresaRepository empresaRepo;
	
	
	
	public void setEventoRepo(EventoRepository eventoRepo) {
		this.eventoRepo = eventoRepo;
	}
	
	public void setEmpresaRepo(EmpresaRepository empresaRepo) {
		this.empresaRepo = empresaRepo;
	}


	@Override
	@Transactional
	public Evento incluirEvento(String nomeEvento, String data, String hora, String local, String nomeEmpresa) {
		// PESQUISA PELA ORGANIZAÇÃO DO EVENTO verifica se a empresa existe
		Empresa empresa = empresaRepo.findByNomeEmpresa(nomeEmpresa);
	
		if(empresa == null) {
			System.out.println("Esta empresa cadastrada para o evento não existe!");
			return null;
		}else {
			// PESQUISA SE JÁ EXISTE EVENTO COM ESTE NOME
			Evento evento = eventoRepo.findByNomeEvento(nomeEvento);
			if(evento == null) {
				evento = new Evento();
				evento.setNomeEvento(nomeEvento);
				evento.setData(data);
				evento.setHora(hora);
				evento.setLocal(local);
				evento.setEmpresa(empresa);
				eventoRepo.save(evento);
				System.out.println("Evento cadastrado com sucesso!");
				return evento;
			}else {
				System.out.println("Este evento já existe!");
				return evento;
			}
		}
	}

	@Override
	@Transactional
	public void excluirEvento(String nomeEvento) {
		// DELETA APENAS UM EVENTO CASO QUEIRA DELETAR MAIS USAR O LIST E DELETALL
		Evento evento = eventoRepo.findByNomeEvento(nomeEvento);
		eventoRepo.delete(evento);
		System.out.println("Evento deletado com sucesso!");
	}

	@Override
	@Transactional
	public void alterarNomeEvento(String nomeAntigo, String nomeNovo) {
		Evento evento = eventoRepo.findByNomeEvento(nomeAntigo);
		if(evento == null) {
			System.out.println("Este Evento não foi encontrado!");
		}else {
			evento.setNomeEvento(nomeNovo);
			eventoRepo.save(evento);
			System.out.println("Nome do Evento alterado com sucesso!");
		}
		
	}

	@Override
	@Transactional
	public void alterarDataEvento(String dataAntiga, String dataNova) {
		// ALTERA TODOS OS EVENTOS DE UMA MESMA DATA PARA OUTRA
		List<Evento> eventos = eventoRepo.buscaDataEvento(dataAntiga);
		if(eventos == null) {
			System.out.println("Não existe evento nesta data!");
		}else {
			// ESSE METODO ALTERA TODAS AS DATAS DE EVENTO NESTA DATA
			eventos.forEach(evento->evento.setData(dataNova));
			eventoRepo.saveAll(eventos);
			System.out.println("Data do Evento alterada com sucesso!");
		}
	}

	@Override
	@Transactional
	public void alterarHoraEvento(String horaAntiga, String horaNova) {
		// ESTE METODO ALTERA APENAS UM HORÁRIO DE EVENTO NÃO IMPORTANDO QUANTOS TENHAM NESTE MESMO HORÁRIO
		Evento evento = eventoRepo.findByHora(horaAntiga);
		if(evento == null) {
			System.out.println("Não possui evento neste horário!");

		}else {
			evento.setHora(horaNova);
			eventoRepo.save(evento);
			System.out.println("Hora do Evento alterada com sucesso!");
		}
	}

	@Override
	@Transactional
	public void alterarLocalEvento(String localAntigo, String localNovo) {
		Evento evento = eventoRepo.findByLocal(localAntigo);
		if(evento == null) {
			System.out.println("Não possui evento neste local!");
		}else {
			evento.setLocal(localNovo);
			eventoRepo.save(evento);
			System.out.println("Local do Evento alterado com sucesso!");
		}
	}

	
	// CONTROLLER
	
	@Override
	public List<Evento> buscarNomeEvento(String nomeEvento) {
		List<Evento> eventos = eventoRepo.findByNomeEventoContains(nomeEvento);
		if(eventos == null) {
			System.out.println("Evento não encontrado");
		}else {
			eventos.forEach(evento->System.out.println(evento));
		}
		return eventos;
	}

	@Override
	public Optional<Evento> buscarIdEvento(Long id) {
		Optional<Evento> evento = eventoRepo.findById(id);
		if(evento == null) {
			System.out.println("Id evento não encontrado");
		}else {
			System.out.println(evento);
		}
		return evento;	
	}

	@Override
	public Empresa buscarEmpresaEvento(Empresa empresa) {
		if(!empresa.getNomeEmpresa().isEmpty()) {
			System.out.println("Empresa encontrado.");
		}else {
			System.out.println("Empresa não encontrado!");
		}
		return empresa;
	}

	@Override
	public List<Evento> todos() {
		List<Evento> eventos = eventoRepo.findAll();
		return eventos;
	}

	@Override
	public Evento salvar(Evento evento) {
		if(!evento.getNomeEvento().isEmpty()) {
			System.out.println("Evento salva com sucesso!");
			return eventoRepo.save(evento);
		}else {
			System.out.println("Não foi possível salvar empresa!");
			return null;
		}
	}

}
