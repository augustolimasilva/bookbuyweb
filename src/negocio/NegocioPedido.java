package negocio;

import java.util.Date;
import java.util.List;

import basica.Cliente;
import basica.Item;
import basica.Mesa;
import basica.Pedido;
import basica.Restaurante;
import basica.Situacao;
import basica.Status;
import dao.DAOFactory;
import dao.DAOPedido;
import util.Fachada;

public class NegocioPedido {

	private DAOPedido daoPedido;
	private NegocioMesa negMesa;

	public NegocioPedido() {
		this.daoPedido = DAOFactory.getDAOPedido();
		this.negMesa = new NegocioMesa();
	}

	public void inserirPedido(Pedido pedido) throws Exception {
//		if(pedido.getCliente() == null){
//			throw new Exception("Informe um cliente.");
//		}
		
		if(pedido.getItens() == null || pedido.getItens().size() <1){
			throw new Exception("Pedido sem itens.");
		}
				
		pedido.setSituacao(Situacao.ATIVO);
		pedido.setStatus(Status.ABERTO);
		if(pedido.getMesa() != null){
			Mesa m = pedido.getMesa();
			m.setStatus(Status.INDISPONIVEL);
			negMesa.alterarMesa(m);
				
		}
		this.daoPedido.inserir(pedido);
	}

	public Pedido buscarPedido(Pedido pedido) throws Exception {

		if (this.daoPedido.buscarId(pedido.getIdPedido()) == null) {
			throw new Exception("Pedido não cadastrado.");
		} else {
			return this.daoPedido.buscarId(pedido.getIdPedido());
		}
	}

	public Pedido buscarPedido(int id) throws Exception {

		if (this.daoPedido.buscarId(id) == null) {
			throw new Exception("Pedido não cadastrado.");
		} else {
			return this.daoPedido.buscarId(id);
		}
	}

	public List<Pedido> buscarTudo() {
		return this.daoPedido.buscarTudo();
	}

	public void removerPedido(Pedido pedido) throws Exception {
		pedido.setSituacao(Situacao.INATIVO);
		this.daoPedido.alterar(pedido);
	}

	public void alterarPedido(Pedido pedido) throws Exception {
		this.daoPedido.alterar(pedido);
		if(pedido.getMesa() != null){
			Mesa m = pedido.getMesa();
			m.setStatus(Status.INDISPONIVEL);
			negMesa.alterarMesa(m);
				
		}
	}

	public List<Item> buscarItensPedido(Pedido pedido) {
		return this.daoPedido.buscarItensPedido(pedido);
	}

	public List<Pedido> buscarPedidosCliente(Cliente cliente) {
		return this.daoPedido.buscarPedidosCliente(cliente);
	}

	public List<Pedido> buscarPedidosRestaurante(Restaurante restaurante) {
		return this.daoPedido.buscarPedidosRestaurante(restaurante);
	}

	public List<Pedido> buscarPedidosData(Date data, int idRestaurante) throws Exception {
		if (idRestaurante == 0) {
			throw new Exception("Favor selecionar um restaurante!");
		}

		if (data == null) {
			throw new Exception("Informe uma data!");
		}

		if (this.daoPedido.buscarPedidosData(data, idRestaurante).size() >= 0) {
			return this.daoPedido.buscarPedidosData(data, idRestaurante);
		} else {
			throw new Exception("Não há pedidos!");
		}
	}

}
