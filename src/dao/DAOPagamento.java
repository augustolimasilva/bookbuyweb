package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import basica.Pagamento;
import basica.Pedido;
import basica.Situacao;

public class DAOPagamento extends DAOGenerico<Pagamento> {

	public DAOPagamento(EntityManager em) {
		super(em);
	}

	public Pagamento buscarPagamentoPedido(Pedido ped) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			String sql = "FROM Pagamento WHERE pedido.idPedido = :idPedido AND situacao = :situacao";
			TypedQuery<Pagamento> query = this.em.createQuery(sql, Pagamento.class);
			query.setParameter("idPedido", ped.getIdPedido());
			query.setParameter("situacao", Situacao.ATIVO);
			return query.getSingleResult();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return null;
	}

	public List<Pagamento> buscarTodosPagamento() {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			em.clear();
			String sql = "FROM Pagamento WHERE situacao = :situacao";
			TypedQuery<Pagamento> query = this.em.createQuery(sql, Pagamento.class);
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

	// public void removerId(int id) {
	// Pagamento pag = this.buscarId(id);
	// this.remover(pag);
	// System.out.println("Pagamento Removido");
	// }
}
