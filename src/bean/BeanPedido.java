package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import basica.Cliente;
import basica.Item;
import basica.Mesa;
import basica.Pagamento;
import basica.Pedido;
import basica.Produto;
import basica.Restaurante;
import basica.Status;
import util.Fachada;
import util.UtilSession;

@SessionScoped
@ManagedBean(name = "beanPedido")
public class BeanPedido implements Serializable {

	private static final long serialVersionUID = 1L;
	Fachada f;
	private Pedido pedido;
	private Date data;
	private Restaurante restaurante;
	private Cliente cliente;
	private Pagamento pagamento;
	private List<Cliente> clientes;
	private List<Item> itens;
	private List<Produto> produtosRestaurante;
	private List<Pedido> pedidos;
	private List<Mesa> mesas;
	private Produto produtoSelecionado;
	private int qtdProduto;
	private Mesa mesa;

	public BeanPedido() {
		f = Fachada.getInstancia();
		this.pedido = new Pedido();
		this.restaurante = new Restaurante();
		this.cliente = new Cliente();
		this.pagamento = new Pagamento();

		/*
		 * this.itens = f.buscarItensPedido(pedido); this.produtosRestaurante =
		 * f.buscarProdutoRestaurante(restaurante); this.clientesRestaurante =
		 * f.buscarTodosClientes(); this.mesasDisponiveis =
		 * f.buscarMesaRestaurante(restaurante);
		 */
	}

	@PostConstruct
	public void init() {
		f = Fachada.getInstancia();
		this.pedido = new Pedido();
		this.restaurante = new Restaurante();
		this.cliente = new Cliente();
		this.clientes = f.buscarTodosClientes();
		this.pagamento = new Pagamento();
		this.mesa = new Mesa();
		this.data = new Date();
		this.produtosRestaurante = new ArrayList<Produto>();
		this.produtoSelecionado = new Produto();
		this.pedidos = new ArrayList<Pedido>();
		this.itens = new ArrayList<Item>();
		this.qtdProduto = 0;
		this.itens = new ArrayList<Item>();
		this.mesas = new ArrayList<Mesa>();
	}
	

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public List<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public int getQtdProduto() {
		return qtdProduto;
	}

	public void setQtdProduto(int qtdProduto) {
		this.qtdProduto = qtdProduto;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public void setProdutosRestaurante(List<Produto> produtosRestaurante) {
		this.produtosRestaurante = produtosRestaurante;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Produto> getProdutosRestaurante() {
		return produtosRestaurante;
	}

	public void carregarObjetos() {
		init();
		try {
			restaurante = (Restaurante) UtilSession.getHttpSessionObject("restaurante");
			mesas = f.buscarMesasDisponiveis(restaurante.getIdRestaurante());
			produtosRestaurante = f.buscarProdutoRestaurante(restaurante);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cadastro", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		}
	}

	public void carregarItens(Pedido p) {
		try {
			itens = f.buscarItensPedido(p.getIdPedido());
//			produtosRestaurante = f.buscarProdutoRestaurante(restaurante);
			pedido = p;

			RequestContext.getCurrentInstance().openDialog("ListarItens");

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Listar", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		}

	}

	public void escolherMesa(AjaxBehaviorEvent event) {
		try {
			mesa = f.buscarIdMesaRestaurante(mesa.getIdMesaRestaurante(), restaurante.getIdRestaurante());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void carregarPedidos() {
		try {
			data = new Date();
			restaurante = (Restaurante) UtilSession.getHttpSessionObject("restaurante");

			pedidos = f.buscarPedidosData(data, restaurante.getIdRestaurante());
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cadastro", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		}
	}

	public void pegarRestaurante() {
		restaurante = (Restaurante) UtilSession.getHttpSessionObject("restaurante");
		if (restaurante != null) {
			try {
				// pedidos = f.buscarPedidosRestaurante(restaurante);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Listar Pedidos", "Escolha um restaurante."));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");

		}
	}

	public void inserirPedido() throws Exception {

		try {
			pedido.setCliente(cliente);
			pedido.setRestaurante(restaurante);
			pedido.setItens(itens);
			pedido.setMesa(mesa);
			pedido.setDataHora(data);
			f.inserirPedido(pedido);

			for (int i = 0; i < itens.size(); i++) {

				itens.get(i).setPedido(pedido);

				f.inserirItem(itens.get(i));

			}
			init();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro", "Pedido efetuado com sucesso"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
			org.primefaces.context.RequestContext.getCurrentInstance().update("formPedido");
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cadastro", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		}

	}

	public void buscarPedidoId() {
		try {
			pedido = f.buscarPedido(pedido.getIdPedido());
			UtilSession.setHttpSessionObject("pedido", pedido);
			itens = f.buscarItensPedido(pedido.getIdPedido());
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pesquisar", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		}
	}

	public void removerPedido(Pedido p) {
		try {
			f.removerPedido(p);
			carregarPedidos();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Remover", "Pedido cancelado com sucesso."));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public List<String> completarTextoCliente(String query) {
	// List<String> nomes = new ArrayList<String>();
	// clientes = f.buscarTodosClientes();
	//
	// for (int i = 0; i < clientes.size(); i++) {
	// nomes.add(clientes.get(i).getLogin());
	// }
	// return nomes;
	// }
	//
	// public void onClienteSelecionado(SelectEvent event) {
	// try {
	// cliente = f.buscarClienteLogin((String) event.getObject());
	//
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// // System.out.println(event.getObject());
	//
	// }

	public List<String> completarTextoProduto(String query) {
		restaurante = (Restaurante) UtilSession.getHttpSessionObject("restaurante");
		if (restaurante != null) {
			List<String> nomes = new ArrayList<String>();
			try {
				produtosRestaurante = f.buscarProdutoRestaurante(restaurante);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (int i = 0; i < produtosRestaurante.size(); i++) {
				nomes.add(produtosRestaurante.get(i).getDescricao());
			}
			return nomes;
		} else {
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Listar Produtos", "Escolha um restaurante."));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
			return null;
		}

	}

	public void onProdutoSelecionado(SelectEvent event) {
		try {
			produtoSelecionado = f.buscarProdutoDescricao((String) event.getObject(), restaurante.getIdRestaurante());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void inserirItem() {
		Item item = new Item();
		item.setProduto(produtoSelecionado);
		item.setQuantidade(qtdProduto);
		item.setValorItem(produtoSelecionado.getValorProduto() * qtdProduto);

		for (int i = 0; i < itens.size(); i++) {

			if (item.getProduto().getIdProduto() == itens.get(i).getProduto().getIdProduto()) {
				itens.get(i).setQuantidade(itens.get(i).getQuantidade() + item.getQuantidade());
				itens.get(i).setValorItem(itens.get(i).getValorItem() + item.getValorItem());
				return;
			}
		}
		if (item.getQuantidade() < 1) {
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cadastro", "Informe uma quantidade."));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");

		} else {
			itens.add(item);
			item.setPedido(pedido);
		}
	}

	public void inserirItemDB() {
		Item item = new Item();
		item.setProduto(produtoSelecionado);
		item.setQuantidade(qtdProduto);
		item.setValorItem(produtoSelecionado.getValorProduto() * qtdProduto);

		for (int i = 0; i < itens.size(); i++) {

			if (item.getProduto().getIdProduto() == itens.get(i).getProduto().getIdProduto()) {
				itens.get(i).setQuantidade(itens.get(i).getQuantidade() + item.getQuantidade());
				itens.get(i).setValorItem(itens.get(i).getValorItem() + item.getValorItem());
				try {
					f.alterarItem(itens.get(i));
				} catch (Exception e) {
					e.printStackTrace();
					FacesContext contexto = FacesContext.getCurrentInstance();
					contexto.addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cadastro", e.getMessage()));
					org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
				}
				return;
			}
		}
		if (item.getQuantidade() < 1) {
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cadastro", "Informe uma quantidade."));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");

		} else {
			itens.add(item);
			item.setPedido(pedido);
			try {
				f.inserirItem(item);
			} catch (Exception e) {
				e.printStackTrace();
				FacesContext contexto = FacesContext.getCurrentInstance();
				contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cadastro", e.getMessage()));
				org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
			}
		}
	}

	public void excluirItemArray(Item item) {
		for (int i = 0; i < itens.size(); i++) {

			if (item.getProduto().getIdProduto() == itens.get(i).getProduto().getIdProduto()) {
				itens.remove(i);
			}

		}
	}

	public void excluirItemDB(Item item) {
		try {
			for (int i = 0; i < itens.size(); i++) {

				if (item.getProduto().getIdProduto() == itens.get(i).getProduto().getIdProduto()) {
					itens.remove(i);
					f.removerItem(item);
					FacesContext contexto = FacesContext.getCurrentInstance();
					contexto.addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Remover", "Item removido com sucesso."));
					org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Remover", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		}
	}

	public void efetuarPagamento(Pedido p) {
		UtilSession.setHttpSessionObject("pedido", p);
		RequestContext.getCurrentInstance().openDialog("CadastroPagamento");

	}

//	public void onPagamentoConcluido(SelectEvent event) {
//		carregarPedidos();
//	}

//	public List<Cliente> completarCliente(String query) {
//		clientes = f.buscarTodosClientes();
//		List<Cliente> clientesFiltrados = new ArrayList<Cliente>();
//
//		for (int i = 0; i < clientes.size(); i++) {
//			Cliente cli = clientes.get(i);
//			if (cli.getLogin().toLowerCase().startsWith(query)) {
//				clientesFiltrados.add(cli);
//			}
//		}
//
//		return clientesFiltrados;
//		<p:autoComplete id="Cliente" value="#{beanPedido.cliente}"
//		converter="converterCliente"
//			completeMethod="#{beanPedido.completarCliente}" var="cliente"
//			itemLabel="#{cliente.login}" itemValue="#{cliente}"
//			>
//			<p:ajax event="itemSelect"
//				listener="#{beanPedido.onClienteSelecionado}" />
//		</p:autoComplete>	
//	}

	public void onClienteSelecionado(SelectEvent e) {
		try {
			cliente = f.buscarClienteLogin((String) e.getObject());
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	public void mostrarMesas(Pedido p) {
		try {
			this.mesas = f.buscarMesasDisponiveis(restaurante.getIdRestaurante());
			this.pedido = p;
			RequestContext.getCurrentInstance().openDialog("EscolherMesa");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SelecionarMesa(Mesa mesa){
		if(pedido.getMesa() != null){
			Mesa m = pedido.getMesa();
			m.setStatus(Status.DISPONIVEL);
			try {
				f.alterarMesa(m);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		
		this.pedido.setMesa(mesa);
		try {
			f.alterarPedido(pedido);
			this.mesas = f.buscarMesasDisponiveis(restaurante.getIdRestaurante());
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterar", "Mesa inserida com sucesso."));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}