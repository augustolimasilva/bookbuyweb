package dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public abstract class DAOGenerico<Entidade> {

	protected EntityManager em;
	protected Class<Entidade> classePersistente;

	@SuppressWarnings("unchecked")
	public DAOGenerico(EntityManager em) {
		this.em = em;
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		classePersistente = (Class<Entidade>) parameterizedType.getActualTypeArguments()[0];
	}

	/**
	 * INSERT
	 */
	public final void inserir(Entidade objeto) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			em.clear();
			getEntityManager().persist(objeto);
			em.flush();
			tx.commit();
			System.out.println(classePersistente.getSimpleName() + " salvo com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
	}

	/**
	 * UPDATE
	 */
	public final void alterar(Entidade objeto) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			em.clear();
			objeto = getEntityManager().merge(objeto);
			em.flush();
			tx.commit();
			System.out.println(classePersistente.getSimpleName() + " alterado com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
	}

	/**
	 * DELETE
	 */
	public final void remover(Entidade objeto) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			em.clear();
			getEntityManager().remove(objeto);
			em.flush();
			tx.commit();
			System.out.println(classePersistente.getSimpleName() + " removido com sucesso");
		} catch (Exception e) {
			e.printStackTrace();

			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
	}

	/**
	 * SELECT
	 */
	public List<Entidade> buscarTudo() {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			em.clear();
			String sql = "from " + classePersistente.getSimpleName();
			TypedQuery<Entidade> query = em.createQuery(sql, classePersistente);
			em.flush();
			tx.commit();
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public final void inserirColecao(Collection<Entidade> colecao) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			em.clear();
			for (Entidade entidade : colecao) {
				getEntityManager().persist(entidade);
			}
			em.flush();
			tx.commit();

			System.out.println(classePersistente.getSimpleName() + " salvos com sucesso: " + colecao.size());
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
	}

	public final Entidade buscarId(int chave) {
		EntityTransaction tx = getEntityManager().getTransaction();
		Entidade instance = null;
		try {
			tx.begin();
			em.clear();
			instance = (Entidade) getEntityManager().find(classePersistente, chave);
			em.flush();
			tx.commit();
		} catch (RuntimeException re) {
			re.printStackTrace();
		}
		return instance;
	}

	public List<Entidade> buscarTudo(Integer indiceInicial, Integer quantidade) {

		try {
			em.clear();
			String sql = "from " + classePersistente.getSimpleName();
			TypedQuery<Entidade> query = em.createQuery(sql, classePersistente);
			query = query.setFirstResult(indiceInicial).setMaxResults(quantidade);
			em.flush();
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}

	public EntityManager getEntityManager() {
		return em;
	}

}
