package negocio;

import basica.Situacao;
import basica.Usuario;
import dao.DAOFactory;
import dao.DAOUsuario;

public class NegocioUsuario {

	private DAOUsuario daoUsuario;

	public NegocioUsuario() {
		this.daoUsuario = DAOFactory.getDAOUsuario();
	}

	public void inserirUsuario(Usuario u) throws Exception {
		Usuario usuarioRetorno = this.daoUsuario.buscarUsuarioLogin(u.getLogin());
		if (usuarioRetorno == null) {
			this.existeLogin(u.getLogin());
			if (verificarCampos(u)) {
				u.setSituacao(Situacao.ATIVO);
				this.daoUsuario.inserir(u);
			}
		} else {
			throw new Exception("Esse LOGIN já está cadastrado, por favor informar outro LOGIN!");
		}
	}

	public void alterarUsuario(Usuario u) throws Exception {
		buscarUsuario(u);
		this.daoUsuario.alterar(u);
	}

	public void removerUsuario(Usuario u) throws Exception {
		buscarUsuario(u);
		u.setSituacao(Situacao.INATIVO);
		this.daoUsuario.alterar(u);
	}

	public Usuario buscarUsuario(Usuario u) throws Exception {
		if (this.daoUsuario.buscarId(u.getIdUsuario()) == null) {
			throw new Exception("Produto não cadastrado.");
		} else {
			return this.daoUsuario.buscarId(u.getIdUsuario());
		}
	}

	public boolean validarLogin(String login, String senha) throws Exception {
		try {
			return daoUsuario.validarLogin(login, senha);
		} catch (Exception e) {
			throw new Exception("Dados Inválidos.");
			// e.printStackTrace();
		}
	}

	public Usuario buscarUsuarioLogin(String login) {
		return daoUsuario.buscarUsuarioLogin(login);
	}

	private boolean verificarCampos(Usuario u) throws Exception {
		if (u.getLogin().trim().isEmpty()) {
			throw new Exception("Favor preencher o campo LOGIN!");
		}
		if (u.getSenha().isEmpty()) {
			throw new Exception("Favor preencher o campo SENHA!");
		}
		return true;
	}

	private void existeLogin(String login) throws Exception {
		if (daoUsuario.existeLogin(login)) {
			throw new Exception("O LOGIN informado já existe!");
		}
	}
}