package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import basica.Promocao;
import basica.Situacao;
import basica.TipoPromo;

public class DAOPromocao extends DAOGenerico<Promocao> {

	public DAOPromocao(EntityManager em) {
		super(em);
	}

	public Promocao buscarPromocao(int idPromocao, int idRestaurante) throws Exception {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			String sql = "FROM Promocao WHERE idPromocao = :idPromocao "
					+ " AND restaurante.idRestaurante = :idRestaurante AND situacao = :situacao";
			TypedQuery<Promocao> query = this.em.createQuery(sql, Promocao.class);
			query.setParameter("idPromocao", idPromocao);
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

	public List<Promocao> buscarData(Date dataInicio, Date dataFinal, int idRestaurante) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			em.clear();
			String sql = "FROM Promocao WHERE dataInicio BETWEEN :dataInicio AND :dataFinal "
					+ "AND restaurante.idRestaurante = :idRestaurante AND situacao = :situacao";
			TypedQuery<Promocao> query = this.em.createQuery(sql, Promocao.class);
			query.setParameter("dataInicio", dataInicio);
			query.setParameter("dataFinal", dataFinal);
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

	public List<Promocao> buscarTipoPromo(TipoPromo tipo, int idRestaurante) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			em.clear();
			String sql = "FROM Promocao WHERE tipo = :tipo AND restaurante.idRestaurante = :idRestaurante AND situacao = :situacao";
			TypedQuery<Promocao> query = this.em.createQuery(sql, Promocao.class);
			query.setParameter("tipo", tipo);
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

	public List<Promocao> buscarPromoRestaurante(int idRestaurante) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			em.clear();
			String sql = "FROM Promocao WHERE restaurante.idRestaurante = :idRestaurante AND situacao = :situacao";
			TypedQuery<Promocao> query = this.em.createQuery(sql, Promocao.class);
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

	public Promocao buscarDescricaoCompleta(String descricao, int idRestaurante) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			String sql = "FROM Promocao WHERE descricao = :descricao "
					+ "AND restaurante.idRestaurante = :idRestaurante AND situacao = :situacao";
			TypedQuery<Promocao> query = this.em.createQuery(sql, Promocao.class);
			query.setParameter("descricao", descricao);
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

	// public void remover(int idPromocao, int idRestaurante) {
	// getEntityManager().getTransaction().begin();
	// Query query = em.createQuery("DELETE FROM Promocao WHERE idPromocao =
	// :idPromocao"
	// + " and restaurante.idRestaurante = :idRestaurante");
	// query.setParameter("idPromocao", idPromocao);
	// query.setParameter("idRestaurante", idRestaurante);
	// query.executeUpdate();
	// getEntityManager().getTransaction().commit();
	// System.out.println("Promoção Removido");
	// }

	// public List<Item> buscarItensPromo(int idPromocao, int idRestaurante) {
	// try {
	// Query query = em.createQuery("FROM Item WHERE promocao.idPromocao =
	// :idPromocao "
	// + "and restaurante.idRestaurante = :idRestaurante");
	// query.setParameter("idPromocao", idPromocao);
	// query.setParameter("idRestaurante", idRestaurante);
	// List<Item> itens = query.getResultList();
	// return itens;
	// } catch (Exception e) {
	// return null;
	// }
	// }

	// public List<Promocao> buscarDescricaoParcial(String descricao, int
	// idRestaurante) {
	// try {
	// Query query = em.createQuery("FROM Promocao WHERE descricao like
	// '%:descricao%' "
	// + "and restaurante.idRestaurante = :idRestaurante");
	// query.setParameter("descricao", descricao);
	// query.setParameter("idRestaurante", idRestaurante);
	// List<Promocao> promos = query.getResultList();
	// return promos;
	// } catch (Exception e) {
	// return null;
	// }
	// }
}