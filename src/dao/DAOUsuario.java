package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import basica.Cliente;
import basica.Situacao;
import basica.Usuario;

public class DAOUsuario extends DAOGenerico<Usuario> {

	public DAOUsuario(EntityManager em) {
		super(em);
	}

	public boolean validarLogin(String login, String senha) throws Exception {
		Query query = em.createQuery("FROM Usuario WHERE login = :login AND senha = :senha AND situacao = :situacao");
		query.setParameter("login", login);
		query.setParameter("senha", senha);
		query.setParameter("situacao", Situacao.ATIVO);
		Usuario usuario = (Usuario) query.getSingleResult();
		if (usuario == null) {
			throw new Exception();
		} else {
			return true;
		}
	}

	public Usuario buscarUsuarioLogin(String login) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			String sql = "FROM Usuario u WHERE u.login = :login AND situacao = :situacao";
			TypedQuery<Usuario> query = this.em.createQuery(sql, Usuario.class);
			query.setParameter("login", login);
			query.setParameter("situacao", Situacao.ATIVO);
			return query.getSingleResult();
		} catch (Exception e) {
			// e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return null;
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
			// e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return false;
	}
}