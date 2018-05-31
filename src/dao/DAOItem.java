package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import basica.Item;
import basica.Produto;
import basica.Restaurante;
import basica.Situacao;

public class DAOItem extends DAOGenerico<Item> {

	private List<Item> itens;

	public DAOItem(EntityManager em) {
		super(em);
	}

	public List<Item> buscarItensPedido(int id) throws Exception {

		Query query = em.createQuery("FROM Item WHERE pedido.idPedido = :idPedido");
		query.setParameter("idPedido", id);

		return this.itens = query.getResultList();

	}
}
