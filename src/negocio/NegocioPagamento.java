package negocio;

import java.util.List;

import basica.Mesa;
import basica.Pagamento;
import basica.Pedido;
import basica.Situacao;
import basica.Status;
import dao.DAOFactory;
import dao.DAOPagamento;

public class NegocioPagamento {

	private DAOPagamento daoPagamento;
	private NegocioMesa negocioMesa;
	private NegocioPedido negocioPedido;

	public NegocioPagamento() {
		this.daoPagamento = DAOFactory.getDAOPagamento();
		this.negocioMesa = new NegocioMesa();
		this.negocioPedido = new NegocioPedido();
	}

	public void inserirPagamento(Pagamento pagamento) throws Exception {
		if (pagamento.getPedido().getIdPedido() <= 0) {
			throw new Exception("Não existe um pedido para esse pagamento!");
		}
		Pedido pedido = new Pedido();
		pedido = negocioPedido.buscarPedido(pagamento.getPedido().getIdPedido());
		Mesa mesa = new Mesa();
		mesa = negocioMesa.buscarIdMesaRestaurante(pedido.getMesa().getIdMesaRestaurante(),
				pedido.getMesa().getRestaurante().getIdRestaurante());
		pagamento.setSituacao(Situacao.ATIVO);
		this.daoPagamento.inserir(pagamento);
		pedido.setStatus(Status.FECHADO);
		negocioPedido.alterarPedido(pedido);
		mesa.setStatus(Status.DISPONIVEL);
		negocioMesa.alterarMesa(mesa);
	}

	public void removerPagamento(Pagamento pagamento) throws Exception {
		if (pagamento.getPedido().getIdPedido() <= 0) {
			throw new Exception("Não existe um pedido para esse pagamento!");
		}
		Pagamento pagamentoRetorno = this.daoPagamento.buscarPagamentoPedido(pagamento.getPedido());
		if (pagamentoRetorno != null) {
			pagamento.setSituacao(Situacao.INATIVO);
			this.daoPagamento.alterar(pagamento);
		} else {
			throw new Exception("Pagamento não encontrado!");
		}
	}

	public void alterarPagamento(Pagamento pagamento) throws Exception {
		if (pagamento.getPedido().getIdPedido() <= 0) {
			throw new Exception("Não existe um pedido para esse pagamento!");
		}
		Pagamento pagamentoRetorno = this.daoPagamento.buscarPagamentoPedido(pagamento.getPedido());
		if (pagamentoRetorno != null) {
			this.daoPagamento.alterar(pagamento);
		} else {
			throw new Exception("Pagamento não encontrado!");
		}
	}

	public Pagamento buscarPagamento(int id) throws Exception {
		if (this.daoPagamento.buscarId(id) == null) {
			throw new Exception("Pagamento não cadastrado!");
		} else {
			return this.daoPagamento.buscarId(id);
		}
	}

	public Pagamento buscarPagamentoPedido(Pedido ped) throws Exception {
		if (ped.getIdPedido() <= 0) {
			throw new Exception("Não existe um pedido para esse pagamento!");
		}
		return this.daoPagamento.buscarPagamentoPedido(ped);
	}

	public Pagamento buscarPagamento(Pagamento pagamento) throws Exception {
		if (this.daoPagamento.buscarId(pagamento.getIdPagamento()) == null) {
			throw new Exception("Pagamento não cadastrado!");
		} else {
			return this.daoPagamento.buscarId(pagamento.getIdPagamento());
		}
	}

	public List<Pagamento> buscarTodosPagamento() {
		return this.daoPagamento.buscarTodosPagamento();
	}

	// public void removerIdPagamento(int id) throws Exception {
	// if (this.daoPagamento.buscarId(id) == null) {
	// throw new Exception("Pagamento não cadastrado!");
	// } else {
	// this.removerIdPagamento(id);
	// }
	// }
}