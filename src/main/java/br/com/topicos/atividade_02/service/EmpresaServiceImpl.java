package br.com.topicos.atividade_02.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.topicos.atividade_02.model.Empresa;
import br.com.topicos.atividade_02.model.Funcionario;
import br.com.topicos.atividade_02.repository.EmpresaRepository;
import br.com.topicos.atividade_02.repository.FuncionarioRepository;


@Service("empresaService")
public class EmpresaServiceImpl implements EmpresaService{

	
	@Autowired
	private EmpresaRepository empresaRepo;
	
	@Autowired
	private FuncionarioRepository funcionarioRepo;

	
	
	
	public void setEmpresaRepo(EmpresaRepository empresaRepo) {
		this.empresaRepo = empresaRepo;
	}

	public void setFuncionarioRepo(FuncionarioRepository funcionarioRepo) {
		this.funcionarioRepo = funcionarioRepo;
	}

	@Override
	@Transactional
	public Empresa incluirEmpresa(String nomeEmpresa) {
		Empresa empresa = empresaRepo.findByNomeEmpresa(nomeEmpresa);
		
		if(empresa == null) {
			empresa = new Empresa();
			empresa.setNomeEmpresa(nomeEmpresa);
			empresaRepo.save(empresa);
			System.out.println("Empresa adicionada com sucesso!");
			return empresa;
		}else {
			System.out.println("Esta empresa já existe!");
			return null;
		}
	}
	
	@Override
	@Transactional
	public void excluirEmpresa(String nomeEmpresa) {
		Empresa empresa = empresaRepo.findByNomeEmpresa(nomeEmpresa);
		if(empresa == null) {
			System.out.println("Esta empresa não existe!");
		}else {
			empresaRepo.delete(empresa);
			System.out.println("Empresa excluida com sucesso!");
		}
	}

	@Override
	@Transactional
	public void incluirFuncionarioEmpresa(String nomeEmpresa, String nomeFuncionario) {
		//Pessoa pessoa = pessoaRepo.findByNome(nomeFuncionario);
		Funcionario funcionario = funcionarioRepo.findByPessoa(nomeFuncionario);
		// ESTÁ DANDO ERRO FICA ESPERANDO UM PARAMETRO nomeFuncionario DO TIPO PESSOA
		if(funcionario == null) {
			System.out.println("Este funcionario não existe!");
		}else {
			Empresa empresa = empresaRepo.findByNomeEmpresa(nomeEmpresa);
			if(empresa == null) {
				System.out.println("Esta empresa não existe!");
			}else {
				empresa.setNomeEmpresa(nomeEmpresa);
				empresa.setFuncionario(funcionario);
				empresaRepo.save(empresa);
				System.out.println("Funcionario incluido na Empresa com sucesso!");
			}
		}
	}

	@Override
	@Transactional
	public void excluirFuncionarioEmpresa(String nomeEmpresa, String nomeFuncionario) {
		Funcionario funcionario = funcionarioRepo.findByPessoa(nomeFuncionario);
		List<Empresa> empresas = empresaRepo.buscaNomeEmpresa(nomeEmpresa);
		
		if(funcionario == null) {
			System.out.println("Este funcionario não existe!");
		}else {
			
			if(empresas == null) {
				System.out.println("Esta empresa não existe!");
			}else {
				for(Empresa empresa : empresas) {
					// verifica se o funcionário da empresa X é o funcionário a ser excluído
					if(empresa.getFuncionario().getPessoa() == funcionario.getPessoa()) {
						empresa.setNomeEmpresa(null);
						empresaRepo.save(empresa);
						System.out.println("Funcionário excluído da Empresa com sucesso!");
					}
				}			
			}
		}
	}

	@Override
	@Transactional
	public void alterarNomeEmpresa(String nomeEmpresaAntigo, String nomeEmpresaNovo) {
		Empresa empresa = empresaRepo.findByNomeEmpresa(nomeEmpresaAntigo);
		
		if(empresa == null) {
			System.out.println("Esta empresa não existe!");
		}else {
			empresa.setNomeEmpresa(nomeEmpresaNovo);
			empresaRepo.save(empresa);
			System.out.println("Nome da empresa alterado com sucesso!");
		}
	}

	// CONTROLLER
	
	@Override
	public List<Empresa> buscarNomeEmpresa(String nomeEmpresa) {
		List<Empresa> empresas = empresaRepo.buscaNomeEmpresa(nomeEmpresa);
		if(empresas == null) {
			System.out.println("Empresa não encontrada");
		}else {
			empresas.forEach(empresa->System.out.println(empresa));
		}
		return empresas;
	}

	@Override
	public Optional<Empresa> buscarIdEmpresa(Long id) {
		Optional<Empresa> empresa = empresaRepo.findById(id);
		if(empresa == null) {
			System.out.println("Id empresa não encontrado");
		}else {
			System.out.println(empresa);
		}
		return empresa;	
	}

	@Override
	public Funcionario buscarFuncionarioEmpresa(Funcionario funcionario) {
		if(!funcionario.getIdentificacao().isEmpty()) {
			System.out.println("Funcionário encontrado.");
		}else {
			System.out.println("Funcionário não encontrado!");
		}
		return funcionario;
	}

	@Override
	public List<Empresa> todos() {
		List<Empresa> empresas = empresaRepo.findAll();
		return empresas;
	}

	@Override
	public Empresa salvar(Empresa empresa) {
		if(!empresa.getNomeEmpresa().isEmpty()) {
			System.out.println("Empresa salva com sucesso!");
			return empresaRepo.save(empresa);
		}else {
			System.out.println("Não foi possível salvar empresa!");
			return null;
		}
	}
	
	
	
}
