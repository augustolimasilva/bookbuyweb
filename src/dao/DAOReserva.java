package dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import basica.Reserva;
import basica.Situacao;
import basica.Status;

public class DAOReserva extends DAOGenerico<Reserva> {

	public DAOReserva(EntityManager em) {
		super(em);
	}

	public void remover(int id) {
		Reserva reserva = this.buscarId(id);
		this.remover(reserva);
		System.out.println("Reserva Removido");
	}

	public void alterar(int id) {
		Reserva reserva = this.buscarId(id);
		this.alterar(reserva);
		System.out.println("Reserva Atualizado");
	}

	public List<Reserva> buscarReservaCliente(int idCliente) {
		Query query = em.createQuery("FROM Reserva WHERE cliente.idCliente = :idCliente AND situacao = :situacao AND status = :status");
		query.setParameter("idCliente", idCliente);
		query.setParameter("situacao", Situacao.ATIVO);
		query.setParameter("status", Status.INDISPONIVEL);
		List<Reserva> reservas = query.getResultList();
		return reservas;
	}

	public List<Reserva> buscarReservaRestaurante(int idRestaurante) {
		Query query = em.createQuery("FROM Reserva WHERE restaurante.idRestaurante = :idRestaurante AND situacao = :situacao");
		query.setParameter("idRestaurante", idRestaurante);
		query.setParameter("situacao", Situacao.ATIVO);
		List<Reserva> reservas = query.getResultList();
		return reservas;
	}

	public List<Reserva> buscarReservaDisponivel(int idRestaurante) {
		Query query = em.createQuery(
				"FROM Reserva WHERE restaurante.idRestaurante = :idRestaurante AND situacao = :situacao AND status = :status");
		query.setParameter("idRestaurante", idRestaurante);
		query.setParameter("situacao", Situacao.ATIVO);
		query.setParameter("status", Status.DISPONIVEL);
		List<Reserva> reservas = query.getResultList();
		return reservas;
	}


	public Reserva buscarReservaDataHora(Date dataHora) {
		Query query = em.createQuery("SELECT * FROM reserva where dataHora = :data AND situacao = :situacao");
		query.setParameter("data", dataHora);
		query.setParameter("situacao", Situacao.ATIVO);
		Reserva reserva = (Reserva) query.getSingleResult();
		return reserva;
	}
	
	public List<Reserva> buscarReservaDataRestaurante(Date dataHora, int idRestaurante) {
		Query query = em.createQuery("FROM Reserva where restaurante.idRestaurante = :idRestaurante AND DATE(dataHora) = :data AND situacao = :situacao AND status = :status");
		query.setParameter("data", dataHora);
		query.setParameter("idRestaurante", idRestaurante);
		query.setParameter("situacao", Situacao.ATIVO);
		query.setParameter("status", Status.DISPONIVEL);
		List<Reserva> reservas = query.getResultList();
		return reservas;
	}
}