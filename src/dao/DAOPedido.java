package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import basica.Cliente;
import basica.Item;
import basica.Pedido;
import basica.Restaurante;
import basica.Situacao;
import basica.Status;

public class DAOPedido extends DAOGenerico<Pedido> {

	List<Item> itens;
	List<Pedido> pedidos;
	
	
	public DAOPedido(EntityManager em) {
		super(em);
	}
	
	public List<Item> buscarItensPedido(Pedido pedido){
		Query query = em.createQuery("FROM Item WHERE pedido.idPedido = :idPedido AND situacao = :situacao");
		query.setParameter("idPedido", pedido.getIdPedido());
		query.setParameter("situacao", Situacao.ATIVO);
		return this.itens = query.getResultList();	
	}
	
	public List<Pedido> buscarPedidosData(Date data, int idRestaurante ){
		Query query = em.createQuery("FROM Pedido where restaurante.idRestaurante = :idRestaurante AND DATE(dataHora) = :data AND situacao = :situacao AND status = :status");
		query.setParameter("data", data);
		query.setParameter("idRestaurante", idRestaurante);
		query.setParameter("situacao", Situacao.ATIVO);
		query.setParameter("status", Status.ABERTO);
		return pedidos = query.getResultList();
	}
	
	public List<Pedido> buscarPedidosCliente(Cliente cliente){
		Query query = em.createQuery("FROM Pedido WHERE cliente.idCliente = :idCliente AND situacao = :situacao");
		query.setParameter("idCliente",  cliente.getIdCliente());
		query.setParameter("situacao", Situacao.ATIVO);
		return this.pedidos = query.getResultList();
	}
	
	public List<Pedido> buscarPedidosRestaurante(Restaurante restaurante){
		Query query = em.createQuery("FROM Pedido WHERE restaurante.idRestaurante = :idRestaurante AND situacao = :situacao");
		query.setParameter("idRestaurante", restaurante.getIdRestaurante());
		query.setParameter("situacao", Situacao.ATIVO);
		return this.pedidos = query.getResultList();
	}

}
