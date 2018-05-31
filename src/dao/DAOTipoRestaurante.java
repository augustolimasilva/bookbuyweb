package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import basica.Situacao;
import basica.TipoRestaurante;

public class DAOTipoRestaurante extends DAOGenerico<TipoRestaurante> {

	public DAOTipoRestaurante(EntityManager em) {
		super(em);
	}

	public TipoRestaurante buscarDescricaoCompleta(String descricao) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			String sql = "FROM TipoRestaurante WHERE descricao = :descricao AND situacao = :situacao";
			TypedQuery<TipoRestaurante> query = this.em.createQuery(sql, TipoRestaurante.class);
			query.setParameter("descricao", descricao);
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

	public List<TipoRestaurante> buscarTipoRestaurante() {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			em.clear();
			String sql = "FROM TipoRestaurante WHERE situacao = :situacao";
			TypedQuery<TipoRestaurante> query = this.em.createQuery(sql, TipoRestaurante.class);
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
}