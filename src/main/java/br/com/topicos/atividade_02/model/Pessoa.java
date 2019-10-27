package br.com.topicos.atividade_02.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.topicos.atividade_02.view.View;

@Entity
@Table(name= "PES_PESSOA")
public class Pessoa {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PES_ID")
	@JsonView({View.PessoaAvancado.class})
	private Long id;
	
	@Column(name = "PES_NOME", length = 100, nullable = false)
	@JsonView({View.PessoaBasico.class})
	private String nome;
	
	@Column(name = "PES_RG", length = 10, unique = true, nullable = false)
	@JsonView({View.PessoaIntermediario.class})
	private String rg;
	
	@Column(name = "PES_IDADE")
	@JsonView({View.PessoaBasico.class})
	private int idade;
	
	@Column(name = "PES_TELEFONE", length = 11)
	@JsonView({View.PessoaIntermediario.class})
	private String telefone;
	
	@Column(name = "PES_SEXO", length = 1)
	@JsonView({View.PessoaBasico.class})
	private String sexo;
	
	// MÉTODOS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
}
