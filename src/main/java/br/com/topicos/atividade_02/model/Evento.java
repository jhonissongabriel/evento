package br.com.topicos.atividade_02.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.topicos.atividade_02.view.View;

@Entity
@Table(name = "EVE_EVENTO")	
public class Evento {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EVE_ID")
	@JsonView({View.EventoAvancado.class})
	private Long id;

	@Column(name = "EVE_NOME", length = 100, unique = true, nullable = false)
	@JsonView({View.EventoBasico.class})
	private String nomeEvento;
	
	@Column(name = "EVE_DATA", length = 10, nullable = false)
	@JsonView({View.EventoBasico.class})
	private String data;
	
	@Column(name = "EVE_HORA", length = 5, nullable = false)
	@JsonView({View.EventoBasico.class})
	private String hora;
	
	@Column(name = "EVE_LOCAL", length = 100, nullable = false)
	@JsonView({View.EventoBasico.class})
	private String local;
	
	
	@ManyToOne
	@JoinColumn(name = "PES_ID")
	@JsonView({View.EventoIntermediario.class})
	private Pessoa pessoa;
	
	
	@ManyToOne
	@JoinColumn(name = "EMP_ID")
	@JsonView({View.EventoAvancado.class})
	private Empresa empresa;
	

	
	// MÉTODOS
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}


	// CHAVE ESTRANGEIRA 
	

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
}
