package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import basica.Produto;
import basica.Restaurante;
import basica.Situacao;

public class DAOProduto extends DAOGenerico<Produto> {

	private List<Produto> produtos;

	public DAOProduto(EntityManager em) {
		super(em);
	}

	public List<Produto> buscarProdutoRestaurante(Restaurante r) throws Exception {
		Query query = em
				.createQuery("FROM Produto WHERE restaurante.idRestaurante = :idRestaurante AND situacao = :situacao");
		query.setParameter("idRestaurante", r.getIdRestaurante());
		query.setParameter("situacao", Situacao.ATIVO);
		return this.produtos = query.getResultList();
	}

	public Produto buscarProdutoDescricao(String descricao, int idRestaurante) throws Exception {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			String sql = "FROM Produto WHERE descricao = :descricao AND situacao = :situacao AND restaurante.idRestaurante = :idRestaurante";
			TypedQuery<Produto> query = this.em.createQuery(sql, Produto.class);
			query.setParameter("descricao", descricao);
			query.setParameter("idRestaurante", idRestaurante);
			query.setParameter("situacao", Situacao.ATIVO);
			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return null;
	}
}
