package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import basica.Restaurante;
import basica.Situacao;
import basica.Usuario;

public class DAORestaurante extends DAOGenerico<Restaurante> {

	List<Restaurante> restaurantes;
	List<String> nomes;
	Restaurante restaurante;

	public DAORestaurante(EntityManager em) {
		super(em);
	}

	public Restaurante buscarRestauranteCnpj(String cnpj) {
		try {
			Query query = em.createQuery("FROM Restaurante WHERE cnpj = :cnpj  AND situacao = :situacao");
			query.setParameter("cnpj", cnpj);
			query.setParameter("situacao", Situacao.ATIVO);
			return this.restaurante = (Restaurante) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public Restaurante buscarRestauranteNome(String nome) {
		try {
			Query query = em.createQuery("FROM Restaurante WHERE nome = :nome AND situacao = :situacao");
			query.setParameter("nome", nome);
			query.setParameter("situacao", Situacao.ATIVO);
			Restaurante res = (Restaurante) query.getSingleResult();
			return res;
		} catch (Exception e) {
			return null;
		}
	}

	public final void removerRestauranteCnpj(String cnpj) {
		getEntityManager().getTransaction().begin();
		Query query = em.createQuery("DELETE Restaurante WHERE cnpj = :cnpj AND situacao = :situacao");
		query.setParameter("situacao", Situacao.ATIVO);
		query.setParameter("cnpj", cnpj);
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
		System.out.println("Restaurante Removido");
	}

	public List<Restaurante> listarRestaurantesBairro(String bairro) {
		try {
			Query query = em.createQuery("FROM Restaurante WHERE bairro = :bairro AND situacao = :situacao ");
			query.setParameter("situacao", Situacao.ATIVO);
			query.setParameter("bairro", bairro);
			return this.restaurantes = query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Restaurante> listarRestaurantesNome(String nome) {
		try {
			Query query = em.createQuery("FROM Restaurante WHERE nome = :nome AND situacao = :situacao");
			query.setParameter("situacao", Situacao.ATIVO);
			query.setParameter("nome", nome);
			return this.restaurantes = query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Restaurante> listarRestaurantesCidade(String cidade) {
		try {
			Query query = em.createQuery("FROM Restaurante WHERE cidade = :cidade AND situacao = :situacao");
			query.setParameter("situacao", Situacao.ATIVO);
			query.setParameter("cidade", cidade);
			return this.restaurantes = query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Restaurante> listarRestaurantesUsuario(Usuario u) {
		try {
			Query query = em
					.createQuery("FROM Restaurante WHERE usuario.idUsuario = :usuario AND situacao = :situacao");
			query.setParameter("usuario", u.getIdUsuario());
			query.setParameter("situacao", Situacao.ATIVO);
			return this.restaurantes = query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public void alterarRestauranteCnpj(String cnpj) {
		Restaurante restaurante = this.buscarRestauranteCnpj(cnpj);
		this.alterar(restaurante);
		System.out.println("Restaurante Atualizado");
	}
}
