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
@Table(name = "FUN_FUNCIONARIO")
public class Funcionario {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FUN_ID")
	@JsonView({View.FuncionarioAvancado.class})
	private Long id;
	
	@Column(name = "FUN_CARGO", length = 50, nullable = false)
	@JsonView({View.FuncionarioBasico.class})
	private String cargo;
	
	@Column(name = "FUN_IDENTIFICACAO", length = 6, unique = true, nullable = false)
	@JsonView({View.FuncionarioIntermediario.class})
	private String identificacao;
	
	
	@ManyToOne
	@JoinColumn(name = "PES_ID")
	@JsonView({View.FuncionarioAvancado.class})
	private Pessoa pessoa;


	// MÉTODOS
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public String getIdentificacao() {
		return identificacao;
	}


	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	
	// CHAVE ESTRANGEIRA

	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	

}
