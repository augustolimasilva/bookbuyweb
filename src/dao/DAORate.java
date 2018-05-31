package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import basica.Cliente;
import basica.Rate;
import basica.Restaurante;
import basica.Situacao;

public class DAORate extends DAOGenerico<Rate> {

	public DAORate(EntityManager em) {
		super(em);
	}

	public Rate buscarPorRate(int rate) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			String sql = "FROM Rate WHERE rate = :rate";
			TypedQuery<Rate> query = this.em.createQuery(sql, Rate.class);
			query.setParameter("rate", rate);
			return query.getSingleResult();
		} catch (Exception e) {
			// e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return null;
	}

	public List<Rate> buscarTodosRate() {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			em.clear();
			String sql = "FROM Rate";
			TypedQuery<Rate> query = this.em.createQuery(sql, Rate.class);
			em.flush();
			tx.commit();
			return query.getResultList();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return null;
	}

	public List<Rate> buscarRateRestaurante(Restaurante r) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			em.clear();
			String sql = "FROM Rate WHERE restaurante.idRestaurante = :idRestaurante";
			TypedQuery<Rate> query = this.em.createQuery(sql, Rate.class);
			query.setParameter("idRestaurante", r.getIdRestaurante());
			em.flush();
			tx.commit();
			return query.getResultList();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return null;
	}

	public List<Rate> buscarRateCliente(Cliente c) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			em.clear();
			String sql = "FROM Rate WHERE cliente.idCliente = :idCliente";
			TypedQuery<Rate> query = this.em.createQuery(sql, Rate.class);
			query.setParameter("idCliente", c.getIdCliente());
			em.flush();
			tx.commit();
			return query.getResultList();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return null;
	}

	public List<Rate> buscarRateRestauranteCliente(Restaurante r, Cliente c) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			em.clear();
			String sql = "FROM Rate WHERE restaurante.idRestaurante = :idRestaurante "
					+ "AND cliente.idCliente = :idCliente";
			TypedQuery<Rate> query = this.em.createQuery(sql, Rate.class);
			query.setParameter("idRestaurante", r.getIdRestaurante());
			query.setParameter("idCliente", c.getIdCliente());
			em.flush();
			tx.commit();
			return query.getResultList();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return null;
	}
}
