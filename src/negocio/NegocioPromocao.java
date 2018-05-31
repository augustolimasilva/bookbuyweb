package negocio;

import java.util.Date;
import java.util.List;

import basica.Promocao;
import basica.Situacao;
import basica.TipoPromo;
import dao.DAOFactory;
import dao.DAOPromocao;

public class NegocioPromocao {

	private DAOPromocao daoPromocao;

	public NegocioPromocao() {
		this.daoPromocao = DAOFactory.getDAOPromocao();
	}

	public void inserirPromocao(Promocao promocao) throws Exception {
		this.validarCampos(promocao);
		Promocao promoRetorno = daoPromocao.buscarDescricaoCompleta(promocao.getDescricao(),
				promocao.getRestaurante().getIdRestaurante());
		if (promoRetorno == null) {
			promocao.setSituacao(Situacao.ATIVO);
			this.daoPromocao.inserir(promocao);
		} else {
			throw new Exception("Essa DESCRIÇÃO já esta cadastrada, favor informar outra DESCRIÇÃO!");
		}
	}

	public void removerPromocao(Promocao promocao) throws Exception {
		if (promocao.getRestaurante() == null || promocao.getRestaurante().getIdRestaurante() == 0) {
			throw new Exception("Favor selecionar um restaurante!");
		}
		Promocao promocaoRetorno = this.daoPromocao.buscarPromocao(promocao.getIdPromocao(),
				promocao.getRestaurante().getIdRestaurante());
		if (promocaoRetorno != null) {
			promocao.setSituacao(Situacao.INATIVO);
			this.daoPromocao.alterar(promocao);
		} else {
			throw new Exception("Promoção não encontrada!");
		}
	}

	public void alterarPromocao(Promocao promocao) throws Exception {
		this.validarCampos(promocao);
		this.daoPromocao.alterar(promocao);
	}

	public Promocao buscarPromocao(Promocao promocao) throws Exception {
		if (promocao.getRestaurante() == null || promocao.getRestaurante().getIdRestaurante() == 0) {
			throw new Exception("Favor selecionar um restaurante!");
		}
		if (this.daoPromocao.buscarId(promocao.getIdPromocao()) != null) {
			return this.daoPromocao.buscarId(promocao.getIdPromocao());
		} else {
			throw new Exception("Promoção não cadastrado!");
		}
	}

	public Promocao buscarPromocao(int idPromocao, int idRestaurante) throws Exception {
		if (idPromocao <= 0) {
			throw new Exception("Favor informar um ID maior que '0'!");
		}
		if (idRestaurante == 0) {
			throw new Exception("Favor selecionar um restaurante!");
		}
		if (this.daoPromocao.buscarId(idPromocao) != null) {
			return this.daoPromocao.buscarPromocao(idPromocao, idRestaurante);
		} else {
			throw new Exception("Promoção não cadastrado!");
		}
	}

	public List<Promocao> buscarTudo() {
		return this.daoPromocao.buscarTudo();
	}

	public List<Promocao> buscarDataPromocao(Date dataInicio, Date dataFinal, int idRestaurante) throws Exception {
		if (idRestaurante == 0) {
			throw new Exception("Favor selecionar um restaurante!");
		}
		if (dataInicio == null) {
			throw new Exception("Favor informar uma DATA INICIAL!");
		}
		if (dataFinal == null) {
			throw new Exception("Favor informar uma DATA FINAL!");
		}
		// if (!dataFinal.after(dataInicio)) {
		// throw new Exception("Favor informar uma DATA INICIO menor que a DATA
		// FINAL!");
		// }
		if (this.daoPromocao.buscarData(dataInicio, dataFinal, idRestaurante) != null) {
			return this.daoPromocao.buscarData(dataInicio, dataFinal, idRestaurante);
		} else {
			throw new Exception("Promoção não encontrada!");
		}
	}

	public List<Promocao> buscarTipoPromocao(TipoPromo tipo, int idRestaurante) throws Exception {
		if (idRestaurante == 0) {
			throw new Exception("Favor selecionar um restaurante!");
		}
		if (this.daoPromocao.buscarTipoPromo(tipo, idRestaurante) != null) {
			return this.daoPromocao.buscarTipoPromo(tipo, idRestaurante);
		} else {
			throw new Exception("Promoção não encontrada!");
		}
	}

	public List<Promocao> buscarPromoRestaurante(int idRestaurante) throws Exception {
		if (idRestaurante == 0) {
			throw new Exception("Favor selecionar um restaurante!");
		}
		if (this.daoPromocao.buscarPromoRestaurante(idRestaurante) != null) {
			return this.daoPromocao.buscarPromoRestaurante(idRestaurante);
		} else {
			throw new Exception("Promoção não encontrada!");
		}
	}

	private void validarCampos(Promocao promocao) throws Exception {
		if (promocao.getRestaurante() == null || promocao.getRestaurante().getIdRestaurante() == 0) {
			throw new Exception("Favor selecionar um restaurante!");
		}
		if (promocao.getDescricao().trim().equals("")) {
			throw new Exception("Favor informar preencher o campo DESCRIÇÃO!");
		}
		if (promocao.getDescricao().length() < 4) {
			throw new Exception("Favor informar uma DESCRIÇÃO com número de caracteres maior que 3!");
		}
		if (promocao.getDescricao().length() > 16) {
			throw new Exception("Favor informar uma DESCRIÇÃO com número de caracteres menor que 16!");
		}
		if (promocao.getDataInicio() == null) {
			throw new Exception("Favor informar uma DATA INICIAL!");
		}
		if (promocao.getDataFinal() == null) {
			throw new Exception("Favor informar uma DATA FINAL!");
		}
		if (promocao.getValor() <= 0) {
			throw new Exception("Favor informar um VALOR maior que '0'!");
		}
		if (!promocao.getDataFinal().after(promocao.getDataInicio())) {
			throw new Exception("Favor informar uma DATA INICIO menor que a DATA FINAL!");
		}
	}

	// public void removerPromocao(int idPromocao, int idRestaurante) throws
	// Exception {
	// if (idPromocao <= 0) {
	// throw new Exception("Favor informar um ID maior que '0'!");
	// }
	// Promocao promocaoRetorno = this.daoPromocao.buscarId(idPromocao);
	// if (promocaoRetorno != null) {
	// this.daoPromocao.remover(idPromocao, idRestaurante);
	// } else {
	// throw new Exception("Promoção não encontrada!");
	// }
	// }

	// public List<Item> buscarItensPromocao(int idPromocao, int idRestaurante)
	// throws Exception {
	// if (idPromocao <= 0) {
	// throw new Exception("Favor informar um ID maior que '0'!");
	// }
	// if (idRestaurante <= 0) {
	// throw new Exception("Favor selecionar um restaurante!");
	// }
	// if (this.daoPromocao.buscarItensPromo(idPromocao, idRestaurante) != null)
	// {
	// return this.daoPromocao.buscarItensPromo(idPromocao, idRestaurante);
	// } else {
	// throw new Exception("Promoção não encontrada!");
	// }
	// }

	// public List<Promocao> buscarDescricaoParcilPromocao(String decricao, int
	// idRestaurante) throws Exception {
	// if (idRestaurante <= 0) {
	// throw new Exception("Favor selecionar um restaurante!");
	// }
	// if (this.daoPromocao.buscarDescricaoParcial(decricao, idRestaurante) !=
	// null) {
	// return this.daoPromocao.buscarDescricaoParcial(decricao, idRestaurante);
	// } else {
	// throw new Exception("Promoção não encontrada!");
	// }
	// }
}
