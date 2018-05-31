package negocio;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import basica.Reserva;
import basica.Situacao;
import basica.Status;
import dao.DAOFactory;
import dao.DAOReserva;

public class NegocioReserva {
	private DAOReserva daoReserva;

	public NegocioReserva() {
		this.daoReserva = DAOFactory.getDAOReserva();
	}

	public boolean verificarCampos(Reserva r) throws Exception {
		// if (r.getCliente() == null) {
		// throw new Exception("Favor informar um cliente para a reserva!");
		// }
		if (r.getRestaurante() == null || r.getRestaurante().getIdRestaurante() == 0) {
			throw new Exception("Favor selecione o restaurante.");
		}
		// if (r.getMesa() == null ||
		// r.getMesa().getStatus().equals(Status.INDISPONIVEL)) {
		// throw new Exception("Favor informar uma mesa disponível para a
		// reserva!");
		// }
		if (r.getData() == null) {
			throw new Exception("Favor informar uma data.");
		}
		Date d = new Date();
		if(r.getData().before(d) && r.getData().equals(d)){
			throw new Exception("Selecione uma data superior a data atual.");
		}

		return true;
	}

	public void inserirReserva(Reserva reserva) throws Exception {
//		Reserva reservaRetorno = this.daoReserva.buscarId(reserva.getIdReserva());
//		if (reservaRetorno == null) {
			if (verificarCampos(reserva)) {
				reserva.setSituacao(Situacao.ATIVO);
				reserva.setStatus(Status.DISPONIVEL);
				this.daoReserva.inserir(reserva);
			}

//		} else {
//			throw new Exception("A reserva já existe, não foi possível cadastrar!");
//		}
	}

	public Reserva buscarReserva(Reserva reserva) throws Exception {
		if (this.daoReserva.buscarId(reserva.getIdReserva()) != null) {
			return this.daoReserva.buscarId(reserva.getIdReserva());
		} else {
			throw new Exception("Reserva não encontrada!");
		}
	}

	public Reserva buscarReserva(int idReserva) throws Exception {
		if (idReserva <= 0) {
			throw new Exception("Favor informar um id maior que '0'!");
		}
		if (this.daoReserva.buscarId(idReserva) != null) {
			return this.daoReserva.buscarId(idReserva);
		} else {
			throw new Exception("Reserva não encontrada!");
		}
	}

	public List<Reserva> buscarReservaCliente(int idCliente) throws Exception {
		if (idCliente <= 0) {
			throw new Exception("Favor informar um id maior que '0'!");
		}
		if (this.daoReserva.buscarReservaCliente(idCliente) != null) {
			return this.daoReserva.buscarReservaCliente(idCliente);
		} else {
			throw new Exception("Não existe reservas cadastradas para esse cliente!");
		}
	}

	public List<Reserva> buscarReservaRestaurante(int idRestaurante) throws Exception {
		if (idRestaurante <= 0) {
			throw new Exception("Favor informar um id maior que '0'!");
		}
		if (this.daoReserva.buscarReservaRestaurante(idRestaurante) != null) {
			return this.daoReserva.buscarReservaRestaurante(idRestaurante);
		} else {
			throw new Exception("Não existe reservas cadastradas para esse restaurante!");
		}
	}


	public Reserva buscarReservaDataHora(Date dataHora) throws Exception {
		if (this.daoReserva.buscarReservaDataHora(dataHora) != null) {
			return this.daoReserva.buscarReservaDataHora(dataHora);
		} else {
			throw new Exception("Reserva não encontrada!");
		}
	}

	public List<Reserva> buscarReservaDataRestaurante(Date dataHora, int idRestaurante) throws Exception {
		if (this.daoReserva.buscarReservaDataRestaurante(dataHora, idRestaurante) != null) {
			return this.daoReserva.buscarReservaDataRestaurante(dataHora, idRestaurante);
		} else {
			throw new Exception("Reserva não encontrada!");
		}
	}

	public List<Reserva> buscarReservaDisponivel(int idRestaurante) throws Exception {
		if (this.daoReserva.buscarReservaDisponivel(idRestaurante) != null) {
			return this.daoReserva.buscarReservaDisponivel(idRestaurante);
		} else {
			throw new Exception("Reserva não encontrada!");
		}
	}

	public List<Reserva> buscarTudo() {
		return this.daoReserva.buscarTudo();
	}

	public void removerReserva(Reserva reserva) throws Exception {
		Reserva reservaRetorno = this.daoReserva.buscarId(reserva.getIdReserva());
		if (reservaRetorno != null) {
			reserva.setSituacao(Situacao.INATIVO);
			this.daoReserva.alterar(reserva);
		} else {
			throw new Exception("Reserva não encontrada!");
		}
	}

	public void alterarReserva(Reserva reserva) throws Exception {
		Reserva reservaRetorno = this.daoReserva.buscarId(reserva.getIdReserva());
		if (reservaRetorno != null) {
			if (verificarCampos(reserva)) {

				if (reserva.getQtdPessoas() <= 0) {
					throw new Exception("Favor informar uma quantidade de pessoas maior que 0.");
				}
				this.daoReserva.alterar(reserva);
			}

		} else {
			throw new Exception("Reserva não encontrada!");
		}
	}
}
