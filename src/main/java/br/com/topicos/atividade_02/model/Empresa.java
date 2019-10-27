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
@Table(name = "EMP_EMPRESA")
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_ID")
	@JsonView({View.EmpresaAvancado.class})
	private Long id;
	
	@Column(name = "EMP_NOME", length = 100, unique = true, nullable = false)
	@JsonView({View.EmpresaBasico.class})
	private String nomeEmpresa;
		
	
	@ManyToOne
	@JoinColumn(name = "FUN_ID")
	@JsonView({View.EmpresaAvancado.class})
	private Funcionario funcionario;

	// MÉTODOS
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}


	// CHAVE ESTRANGEIRA 
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
}
