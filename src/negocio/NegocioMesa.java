package negocio;

import java.util.List;

import basica.Mesa;
import basica.Situacao;
import basica.Status;
import dao.DAOFactory;
import dao.DAOMesa;

public class NegocioMesa {

	private DAOMesa daoMesa;

	public NegocioMesa() {
		this.daoMesa = DAOFactory.getDAOMesa();
	}

	public void inserirMesa(Mesa mesa) throws Exception {
		this.validaCampoMesa(mesa);
		Mesa mesaRetorno = this.daoMesa.buscarIdMesaRestaurante(mesa.getIdMesaRestaurante(),
				mesa.getRestaurante().getIdRestaurante());
		if (mesaRetorno == null) {
			mesa.setSituacao(Situacao.ATIVO);
			this.daoMesa.inserir(mesa);
		} else {
			throw new Exception("Já existe uma mesa cadastrada com esse ID, favor escolher outro ID!");
		}
	}

	public void removerMesa(Mesa mesa) throws Exception {
		if (mesa.getRestaurante() == null || mesa.getRestaurante().getIdRestaurante() == 0) {
			throw new Exception("Favor selecionar um restaurante!");
		}
		Mesa mesaRetorno = this.daoMesa.buscarIdMesaRestaurante(mesa.getIdMesaRestaurante(),
				mesa.getRestaurante().getIdRestaurante());
		if (mesaRetorno != null) {
			mesa.setSituacao(Situacao.INATIVO);
			this.daoMesa.alterar(mesa);
		} else {
			throw new Exception("Mesa não cadastrada!");
		}
	}

	public void alterarMesa(Mesa mesa) throws Exception {
		this.validaCampoMesa(mesa);
		Mesa mesaRetorno = this.daoMesa.buscarIdMesaRestaurante(mesa.getIdMesaRestaurante(),
				mesa.getRestaurante().getIdRestaurante());
		if (mesaRetorno != null) {
			this.daoMesa.alterar(mesa);
		} else {
			throw new Exception("Mesa não cadastrada!");
		}
	}

	public Mesa buscarIdMesaRestaurante(int idMesaRestaurante, int idRestaurante) throws Exception {
		if (idMesaRestaurante <= 0) {
			throw new Exception("Favor informar um ID maior que '0'!");
		}
		if (idRestaurante == 0) {
			throw new Exception("Favor selecionar um restaurante!");
		}
		if (this.daoMesa.buscarIdMesaRestaurante(idMesaRestaurante, idRestaurante) != null) {
			return this.daoMesa.buscarIdMesaRestaurante(idMesaRestaurante, idRestaurante);
		} else {
			throw new Exception("Mesa não cadastrada!");
		}
	}

	public List<Mesa> buscarMesaRestaurante(int idRestaurante) throws Exception {
		if (idRestaurante == 0) {
			throw new Exception("Favor selecionar um restaurante!");
		}
		if (this.daoMesa.buscarMesaRestaurante(idRestaurante).size() > 0) {
			return this.daoMesa.buscarMesaRestaurante(idRestaurante);
		} else {
			throw new Exception("Mesa não cadastrada!");
		}
	}

	public List<Mesa> buscarTudo() throws Exception {
		return this.daoMesa.buscarTudo();
	}

	public List<Mesa> buscarMesaStatus(Status status, int idRestaurante) throws Exception {
		if (idRestaurante == 0) {
			throw new Exception("Favor selecionar um restaurante!");
		}
		return this.daoMesa.buscarMesaStatus(status, idRestaurante);
	}

	public List<Mesa> buscarQtdLugar(int qtdLugar, int idRestaurante) throws Exception {
		if (qtdLugar <= 0) {
			throw new Exception("Favor informar uma QUANTIDADE DE LUGAR	maior que '0'!");
		}
		if (idRestaurante == 0) {
			throw new Exception("Favor selecionar um restaurante!");
		}
		return this.daoMesa.buscarQtdLugar(qtdLugar, idRestaurante);
	}

	private void validaCampoMesa(Mesa mesa) throws Exception {
		if (mesa.getIdMesaRestaurante() <= 0) {
			throw new Exception("Favor informar a mesa!");
		}
		if (mesa.getStatus() == null) {
			throw new Exception("Favor informar um STATUS!");
		}
		if (mesa.getQtdLugar() <= 0) {
			throw new Exception("Favor informar a quantidade de lugares maior que '0'!");
		}
		if (mesa.getRestaurante() == null || mesa.getRestaurante().getIdRestaurante() == 0) {
			throw new Exception("Favor selecionar um restaurante!");
		}
	}

	public List<Mesa> buscarMesasDisponiveis(int idRestaurante) throws Exception {
		if (idRestaurante == 0) {
			throw new Exception("Favor selecionar um restaurante!");
		}
		if (this.daoMesa.buscarMesasDisponiveis(idRestaurante).size() > 0) {
			return this.daoMesa.buscarMesasDisponiveis(idRestaurante);
		} else {
			throw new Exception("Mesa não cadastrada!");
		}
	}

	// public Mesa buscarMesa(int idMesa) throws Exception {
	// if (idMesa <= 0) {
	// throw new Exception("Favor informar um ID maior que '0'!");
	// }
	// if (this.daoMesa.buscarId(idMesa) != null) {
	// return this.daoMesa.buscarId(idMesa);
	// } else {
	// throw new Exception("Mesa não cadastrada!");
	// }
	// }

	// public void removerMesa(int idMesa, int idRestaurante) throws Exception {
	// if (idMesa <= 0) {
	// throw new Exception("Favor informar um ID maior que '0'!");
	// }
	// if (idRestaurante <= 0) {
	// throw new Exception("Favor selecionar um Restaurante!");
	// }
	// Mesa mesaRetorno = this.daoMesa.buscarId(idMesa);
	// if (mesaRetorno != null) {
	// this.daoMesa.remover(idMesa, idRestaurante);
	// } else {
	// throw new Exception("Mesa não cadastrada!");
	// }
	// }

	// public void removerMesa(Mesa mesa) throws Exception {
	// Mesa mesaRetorno =
	// this.daoMesa.buscarIdMesaRestaurante(mesa.getIdMesaRestaurante(),
	// mesa.getRestaurante().getIdRestaurante());
	// if (mesaRetorno != null) {
	// this.daoMesa.remover(mesa);
	// } else {
	// throw new Exception("Mesa não cadastrada!");
	// }
	// }
}