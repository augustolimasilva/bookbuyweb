package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import basica.Mesa;
import basica.Situacao;
import basica.Status;

public class DAOMesa extends DAOGenerico<Mesa> {

	public DAOMesa(EntityManager em) {
		super(em);
	}

	public List<Mesa> buscarMesaRestaurante(int idRestaurante) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			em.clear();
			String sql = "FROM Mesa WHERE restaurante.idRestaurante = :idRestaurante AND situacao = :situacao";
			TypedQuery<Mesa> query = this.em.createQuery(sql, Mesa.class);
			query.setParameter("idRestaurante", idRestaurante);
			query.setParameter("situacao", Situacao.ATIVO);
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
	
	public List<Mesa> buscarMesasDisponiveis(int idRestaurante) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			em.clear();
			String sql = "FROM Mesa WHERE restaurante.idRestaurante = :idRestaurante AND situacao = :situacao AND status = :status";
			TypedQuery<Mesa> query = this.em.createQuery(sql, Mesa.class);
			query.setParameter("idRestaurante", idRestaurante);
			query.setParameter("situacao", Situacao.ATIVO);
			query.setParameter("status", Status.DISPONIVEL);
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

	public Mesa buscarIdMesaRestaurante(int idMesaRestaurante, int idRestaurante) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			String sql = "FROM Mesa WHERE idMesaRestaurante = :idMesaRestaurante"
					+ " and restaurante.idRestaurante = :idRestaurante AND situacao = :situacao";
			TypedQuery<Mesa> query = this.em.createQuery(sql, Mesa.class);
			query.setParameter("idMesaRestaurante", idMesaRestaurante);
			query.setParameter("idRestaurante", idRestaurante);
			query.setParameter("situacao", Situacao.ATIVO);
			return query.getSingleResult();
		} catch (Exception e) {
			//e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return null;
	}

	public List<Mesa> buscarMesaStatus(Status status, int idRestaurante) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			em.clear();
			String sql = "FROM Mesa WHERE status = :status AND restaurante.idRestaurante = :idRestaurante"
					+ " AND situacao = :situacao";
			TypedQuery<Mesa> query = this.em.createQuery(sql, Mesa.class);
			query.setParameter("status", status);
			query.setParameter("idRestaurante", idRestaurante);
			query.setParameter("situacao", Situacao.ATIVO);
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

	public List<Mesa> buscarQtdLugar(int qtdLugar, int idRestaurante) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			em.clear();
			String sql = "FROM Mesa WHERE qtdLugar >= :qtdLugar AND restaurante.idRestaurante = :idRestaurante"
					+ " AND situacao = :situacao";
			TypedQuery<Mesa> query = this.em.createQuery(sql, Mesa.class);
			query.setParameter("qtdLugar", qtdLugar);
			query.setParameter("idRestaurante", idRestaurante);
			query.setParameter("situacao", Situacao.ATIVO);
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

	// public void remover(int idMesa, int idRestaurante) {
	// getEntityManager().getTransaction().begin();
	// Query query = em
	// .createQuery("DELETE FROM Mesa WHERE idMesa = :idMesa and
	// restaurante.idRestaurante = :idRestaurante");
	// query.setParameter("idMesa", idMesa);
	// query.setParameter("idRestaurante", idRestaurante);
	// query.executeUpdate();
	// getEntityManager().getTransaction().commit();
	// System.out.println("Restaurante Removido");
	// }

	// public void removerIdMesaRestaurante(int idMesaRestaurante, int
	// idRestaurante) {
	// getEntityManager().getTransaction().begin();
	// Query query = em.createQuery("DELETE FROM Mesa WHERE idMesaRestaurante =
	// :idMesaRestaurante"
	// + " and restaurante.idRestaurante = :idRestaurante AND situacao =
	// :situacao");
	// query.setParameter("idMesaRestaurante", idMesaRestaurante);
	// query.setParameter("idRestaurante", idRestaurante);
	// query.setParameter("situacao", Situacao.ATIVO);
	// query.executeUpdate();
	// getEntityManager().getTransaction().commit();
	// System.out.println("Restaurante Removido");
	// }
}