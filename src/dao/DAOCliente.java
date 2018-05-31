package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import basica.Cliente;
import basica.Item;
import basica.Pedido;
import basica.Situacao;

public class DAOCliente extends DAOGenerico<Cliente> {

	public DAOCliente(EntityManager em) {
		super(em);
	}
	
	private List<Cliente> clientes;

	public Cliente buscarClienteLogin(String login) throws Exception {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			String sql = "FROM Cliente WHERE login = :login AND situacao = :situacao";
			TypedQuery<Cliente> query = this.em.createQuery(sql, Cliente.class);
			query.setParameter("login", login);
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
	
	public List<Cliente> buscarClienteLoginList(String login){
//		Query query = em.createQuery("FROM Cliente WHERE login LIKE :login% AND situacao = :situacao");
//		query.setParameter("login", login);
//		query.setParameter("situacao", Situacao.ATIVO);
//		return this.clientes = query.getResultList();	
		
		Session session = em.unwrap(Session.class);
		Criteria cri = session.createCriteria(Cliente.class);
		cri.add(Restrictions.ilike("login", login.toUpperCase(),MatchMode.START));
		return cri.list();
	}

	public Cliente buscarClienteEmail(String email) throws Exception {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			String sql = "FROM Cliente WHERE email = :email AND situacao = :situacao";
			TypedQuery<Cliente> query = this.em.createQuery(sql, Cliente.class);
			query.setParameter("email", email);
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

	public void validarLogin(String login, String senha) throws Exception {
		try {
			Query query = em
					.createQuery("FROM Cliente WHERE login = :login AND senha = :senha AND situacao = :situacao");
			query.setParameter("login", login);
			query.setParameter("senha", senha);
			query.setParameter("situacao", Situacao.ATIVO);
		} catch (Exception e) {
			throw new Exception("Cliente não existe!");
		}
	}

	public boolean existeLogin(String login) throws Exception {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			String sql = "FROM Cliente WHERE login = :login AND situacao = :situacao";
			TypedQuery<Cliente> query = this.em.createQuery(sql, Cliente.class);
			query.setParameter("login", login);
			query.setParameter("situacao", Situacao.ATIVO);
			query.getSingleResult();
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return false;
	}

	// public void atualizarClienteEmail(String email) throws Exception {
	// Cliente cliente = this.buscarClienteEmail(email);
	// this.alterar(cliente);
	// System.out.println("Cliente Atualizado");
	// }
	//
	// public void removerClienteEmail(String email) {
	// getEntityManager().getTransaction().begin();
	// Query query = em.createQuery("DELETE FROM Cliente WHERE email = :email
	// AND situacao = :situacao");
	// query.setParameter("email", email);
	// query.executeUpdate();
	// getEntityManager().getTransaction().commit();
	// System.out.println("Cliente Removido");
	// }

	// public Cliente buscarClienteTelefone(String telefone) {
	// EntityTransaction tx = getEntityManager().getTransaction();
	// try {
	// String sql = "FROM Cliente WHERE telefone = :telefone AND situacao =
	// :situacao";
	// TypedQuery<Cliente> query = this.em.createQuery(sql, Cliente.class);
	// query.setParameter("telefone", telefone);
	// query.setParameter("situacao", Situacao.ATIVO);
	// return query.getSingleResult();
	// } catch (Exception e) {
	// e.printStackTrace();
	// if (tx != null && tx.isActive()) {
	// tx.rollback();
	// }
	// }
	// return null;
	// }

	// public List<Cliente> listarClientes(String nome) throws Exception {
	// EntityTransaction tx = getEntityManager().getTransaction();
	// try {
	// tx.begin();
	// em.clear();
	// String sql = "FROM Cliente WHERE nome = :nome AND situacao = :situacao";
	// TypedQuery<Cliente> query = this.em.createQuery(sql, Cliente.class);
	// query.setParameter("nome", nome);
	// query.setParameter("situacao", Situacao.ATIVO);
	// em.flush();
	// tx.commit();
	// return query.getResultList();
	// } catch (Exception e) {
	// if (tx != null && tx.isActive()) {
	// tx.rollback();
	// }
	// }
	// return null;
	// }
}