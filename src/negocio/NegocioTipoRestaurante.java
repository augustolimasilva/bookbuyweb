package negocio;

import java.util.List;

import basica.Situacao;
import basica.TipoRestaurante;
import dao.DAOFactory;
import dao.DAOTipoRestaurante;

public class NegocioTipoRestaurante {

	private DAOTipoRestaurante daoTipoRestaurante;

	public NegocioTipoRestaurante() {
		this.daoTipoRestaurante = DAOFactory.getDAOTipoRestaurante();
	}

	private boolean verificarCampos(TipoRestaurante tipoRestaurante) throws Exception {
		if (tipoRestaurante.getDescricao().trim().isEmpty()) {
			throw new Exception("Preencha o campo descrição!");
		}
		return true;
	}

	public void inserirTipoRestaurante(TipoRestaurante tipoRestaurante) throws Exception {
		TipoRestaurante tr = this.daoTipoRestaurante.buscarDescricaoCompleta(tipoRestaurante.getDescricao());
		if (tr == null) {
			if (verificarCampos(tipoRestaurante)) {
				tipoRestaurante.setSituacao(Situacao.ATIVO);
				this.daoTipoRestaurante.inserir(tipoRestaurante);
			}
		} else {
			throw new Exception("A descrição informada já existe!");
		}
	}

	public void removerTipoRestaurante(TipoRestaurante tipoRestaurante) throws Exception {
		TipoRestaurante tr = this.buscarTipoRestaurante(tipoRestaurante);
		if (tr != null) {
			tipoRestaurante.setSituacao(Situacao.INATIVO);
			this.daoTipoRestaurante.alterar(tipoRestaurante);
		} else {
			throw new Exception("Tipo não encontrado!");
		}
	}

	public void alterarTipoRestaurante(TipoRestaurante tipoRestaurante) throws Exception {
		this.buscarTipoRestaurante(tipoRestaurante);
		if (verificarCampos(tipoRestaurante)) {
			this.daoTipoRestaurante.alterar(tipoRestaurante);
		}
	}

	public TipoRestaurante buscarTipoRestaurante(TipoRestaurante tipoRestaurante) throws Exception {
		if (this.daoTipoRestaurante.buscarId(tipoRestaurante.getIdTipo()) == null) {
			throw new Exception("Tipo não cadastrado!");
		} else {
			return this.daoTipoRestaurante.buscarId(tipoRestaurante.getIdTipo());
		}
	}

	public TipoRestaurante buscarTipoRestaurante(int id) throws Exception {
		if (this.daoTipoRestaurante.buscarId(id) == null) {
			throw new Exception("Tipo não cadastrado!");
		} else {
			return this.daoTipoRestaurante.buscarId(id);
		}
	}

	// -------------//
	public TipoRestaurante buscarDescricaoCompleta(String descricao) throws Exception {
		if (descricao.trim().isEmpty()) {
			throw new Exception("Preencha o campo descrição!");
		}
		return this.daoTipoRestaurante.buscarDescricaoCompleta(descricao);
	}

	public List<TipoRestaurante> buscarTodosTipoRestaurante() {
		return this.daoTipoRestaurante.buscarTipoRestaurante();
	}
}
