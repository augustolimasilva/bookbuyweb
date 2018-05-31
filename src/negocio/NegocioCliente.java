package negocio;

import java.util.List;

import javax.persistence.Query;

import basica.Cliente;
import basica.Situacao;
import dao.DAOCliente;
import dao.DAOFactory;
import util.Validacao;

public class NegocioCliente {

	private DAOCliente daoCliente;

	public NegocioCliente() {
		this.daoCliente = DAOFactory.getDAOCliente();
	}

	public void inserirCliente(Cliente cli) throws Exception {
		this.validaCampoBrancoCliente(cli);
		Cliente clienteRetorno = this.daoCliente.buscarClienteEmail(cli.getEmail());
		if (clienteRetorno == null) {
			this.validaCampoCliente(cli);
			this.existeLogin(cli.getLogin().trim());
			cli.setSituacao(Situacao.ATIVO);
			this.daoCliente.inserir(cli);
		} else {
			throw new Exception("Esse email já está cadastrado, por favor inserir outro email!");
		}
	}

	public void removerCliente(Cliente cli) throws Exception {
		if (cli.getEmail().length() == 0) {
			throw new Exception("Preencha o campo EMAIL!");
		}
		Validacao.isEmailValid(cli.getEmail());
		Cliente clienteRetorno = this.daoCliente.buscarClienteEmail(cli.getEmail());
		if (clienteRetorno != null) {
			cli.setSituacao(Situacao.INATIVO);
			this.daoCliente.alterar(cli);
		} else {
			throw new Exception("Cliente não cadastrado!");
		}
	}

	public void alterarCliente(Cliente cli) throws Exception {
		this.validaCampoBrancoCliente(cli);
		Cliente clienteRetorno = this.daoCliente.buscarClienteEmail(cli.getEmail());
		if (clienteRetorno == null) {
			throw new Exception("Cliente não cadastrado!");
		} else {
			this.validaCampoCliente(cli);
			this.daoCliente.alterar(cli);
		}
	}

	public Cliente buscarClienteEmail(String email) throws Exception {
		if (email.trim().equals("")) {
			throw new Exception("Preencha o campo EMAIL!");
		}
		Validacao.isEmailValid(email);
		if (this.daoCliente.buscarClienteEmail(email) == null) {
			throw new Exception("Cliente não cadastrado.");
		} else {
			return this.daoCliente.buscarClienteEmail(email);
		}
	}

	public Cliente buscarClienteLogin(String login) throws Exception {
		if (login.length() < 5) {
			throw new Exception("Informe um login válido com no mínimo 5 caracteres.");
		}
		if (this.daoCliente.buscarClienteLogin(login) == null) {
			throw new Exception("Cliente não cadastrado.");
		} else {
			return this.daoCliente.buscarClienteLogin(login);
		}
	}
	

	public List<Cliente> buscarClienteLoginList(String login){

		return this.daoCliente.buscarClienteLoginList(login);	
	}


	public Cliente buscarCliente(Cliente cli) throws Exception {
		if (this.daoCliente.buscarId(cli.getIdCliente()) == null) {
			throw new Exception("Cliente não cadastrado!");
		} else {
			return this.daoCliente.buscarId(cli.getIdCliente());
		}
	}

	public List<Cliente> buscarTudo() {
		return this.daoCliente.buscarTudo();
	}

	private void validaCampoCliente(Cliente cli) throws Exception {
		// VALIDAR NOME
		if (cli.getNome().trim().equals("")) {
			throw new Exception("Favor preencher o campo NOME!");
		}
		if (cli.getNome().length() < 4) {
			throw new Exception("O NOME não pode ter menos de '4' caracteres!");
		}
		if (cli.getNome().length() > 50) {
			throw new Exception("O NOME não pode ter mais de '50' caracteres!");
		}
		// VALIDAR EMAIL
		if (cli.getEmail().trim().equals("")) {
			throw new Exception("Favor preencher o campo EMAIL!");
		}
		Validacao.isEmailValid(cli.getEmail());
		// VALIDAR TELEFONE
		if (cli.getTelefone().trim().equals("")) {
			throw new Exception("Favor preencher o campo TELEFONE!");
		}
		if (cli.getTelefone().length() > 14) {
			throw new Exception("Por favor preencha o campo TELEFONE com um número válido!");
		}
		// VALIDAR LOGIN
		if (cli.getLogin().trim().equals("")) {
			throw new Exception("Favor preencher o campo LOGIN!");
		}
		if (cli.getLogin().length() < 5) {
			throw new Exception("O LOGIN não pode ter menos de '5' caracteres!");
		}
		if (cli.getLogin().length() > 15) {
			throw new Exception("O LOGIN não pode ter mais de '15' caracteres!");
		}
		// VALIDAR SENHA
		if (cli.getSenha().trim().equals("")) {
			throw new Exception("Favor preencher o campo SENHA!");
		}
		if (cli.getSenha().length() < 4) {
			throw new Exception("A SENHA não pode ter menos de '4' caracteres!");
		}
		if (cli.getSenha().length() > 10) {
			throw new Exception("A SENHA não pode ter mais de '9' caracteres!");
		}
	}

	private void validaCampoBrancoCliente(Cliente cli) throws Exception {
		if (cli.getSenha().trim().equals("") && cli.getNome().trim().equals("") && cli.getEmail().trim().equals("")
				&& cli.getTelefone().trim().equals("") && cli.getLogin().trim().equals("")) {
			throw new Exception("Favor preencher todos os campos!");
		}
	}

	private void existeLogin(String login) throws Exception {
		if (daoCliente.existeLogin(login)) {
			throw new Exception("O LOGIN informado já existe!");
		}
	}

	public void validarLogin(String login, String senha) throws Exception {
		if (login.trim().equals("") && senha.trim().equals("")) {
			throw new Exception("Favor preencher todos os campos!");
		}
		// VALIDAR LOGIN
		if (login.trim().equals("")) {
			throw new Exception("Favor preencher o campo LOGIN!");
		}
		if (login.length() < 5) {
			throw new Exception("O LOGIN não pode ter menos de '5' caracteres!");
		}
		if (login.length() > 15) {
			throw new Exception("O LOGIN não pode ter mais de '15' caracteres!");
		}
		// VALIDAR SENHA
		if (senha.trim().equals("")) {
			throw new Exception("Favor preencher o campo SENHA!");
		}
		if (senha.length() < 4) {
			throw new Exception("A SENHA não pode ter menos de '4' caracteres!");
		}
		if (senha.length() > 9) {
			throw new Exception("A SENHA não pode ter mais de '9' caracteres!");
		}
		daoCliente.validarLogin(login, senha);
	}

	// public void removerClienteEmail(String email) throws Exception {
	// if (email.length() == 0) {
	// throw new Exception("Preencha o campo EMAIL!");
	// }
	// Validacao.isEmailValid(email);
	// Cliente clienteRetorno = this.daoCliente.buscarClienteEmail(email);
	// if (clienteRetorno == null) {
	// throw new Exception("Cliente não cadastrado!");
	// } else {
	// clienteRetorno.setSituacao(Situacao.INATIVO);
	// this.daoCliente.alterar(clienteRetorno);
	// }
	// }

	// public Cliente buscarCliente(int id) throws Exception {
	// if (id <= 0) {
	// throw new Exception("Por favor informar um ID maior que 0!");
	// }
	// if (this.daoCliente.buscarId(id) == null) {
	// throw new Exception("Cliente não cadastrado!");
	// } else {
	// return this.daoCliente.buscarId(id);
	// }
	// }

	// public Cliente buscarClienteTelefone(String telefone) throws Exception {
	// if (telefone.length() == 0) {
	// throw new Exception("Por favor preencha o campo TELEFONE!");
	// }
	// if (this.daoCliente.buscarClienteTelefone(telefone) == null) {
	// throw new Exception("Cliente não cadastrado!");
	// } else {
	// return this.daoCliente.buscarClienteTelefone(telefone);
	// }
	// }

	// public List<Cliente> listarClientes(String nome) throws Exception {
	// if (nome.length() < 5) {
	// throw new Exception("Informe um NOME válido.");
	// }
	// return this.daoCliente.listarClientes(nome);
	// }

	// public void alterarClienteEmail(String email) throws Exception {
	// if (email.trim().equals("")) {
	// throw new Exception("Preencha o campo EMAIL!");
	// }
	// Validacao.isEmailValid(email);
	// Cliente clienteRetorno = this.daoCliente.buscarClienteEmail(email);
	// if (clienteRetorno == null) {
	// throw new Exception("Cliente não cadastrado!");
	// } else {
	// this.daoCliente.alterar(clienteRetorno);
	// }
	// }
}
