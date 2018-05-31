package bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import basica.Cliente;
import basica.Item;
import basica.Pedido;
import basica.Produto;
import basica.Reserva;
import basica.Restaurante;
import basica.Status;
import util.Fachada;
import util.UtilSession;

@SessionScoped
@ManagedBean
public class BeanReserva {

	Fachada f;
	private Reserva reserva;
	private Cliente cliente;
	private Date data;
	private Restaurante restaurante;
	private List<Restaurante> restaurantes;
	private List<Reserva> reservas;
	private boolean skip = false;
	private int qtdPessoas;
	private Pedido pedido;
	private List<Item> itens;
	private Produto produtoSelecionado;
	private List<Produto> produtosRestaurante;
	private int qtdProduto;
	private int qtdReserva;

	@PostConstruct
	public void init() {
		try {
			this.f = Fachada.getInstancia();
			this.restaurante = new Restaurante();
			this.cliente = new Cliente();
			this.reserva = new Reserva();
			this.reserva.setData(new Date());
			this.restaurantes = f.buscarTodosRestaurante();
			this.reservas = f.buscarReservaRestaurante(restaurante.getIdRestaurante());
			this.data = new Date();
			this.pedido = new Pedido();
			this.itens = new ArrayList<Item>();
			this.produtoSelecionado = new Produto();
			this.produtosRestaurante = new ArrayList<Produto>();
			this.qtdProduto = 0;
			this.qtdReserva = 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getQtdReserva() {
		return qtdReserva;
	}

	public void setQtdReserva(int qtdReserva) {
		this.qtdReserva = qtdReserva;
	}

	public int getQtdProduto() {
		return qtdProduto;
	}

	public void setQtdProduto(int qtdProduto) {
		this.qtdProduto = qtdProduto;
	}

	public List<Produto> getProdutosRestaurante() {
		return produtosRestaurante;
	}

	public void setProdutosRestaurante(List<Produto> produtosRestaurante) {
		this.produtosRestaurante = produtosRestaurante;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public int getQtdPessoas() {
		return qtdPessoas;
	}

	public void setQtdPessoas(int qtdPessoas) {
		this.qtdPessoas = qtdPessoas;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void pegarRestaurante() {
		restaurante = (Restaurante) UtilSession.getHttpSessionObject("restaurante");
		if (restaurante != null) {
			try {
				reservas = f.buscarReservaRestaurante(restaurante.getIdRestaurante());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Listar Reserva", "Escolha um restaurante."));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");

		}
	}

	public void pegarCliente() {
		cliente = (Cliente) UtilSession.getHttpSessionObject("cliente");
		if (cliente != null) {
			try {
				reservas = f.buscarReservaCliente(cliente.getIdCliente());
			} catch (Exception e) {

				e.printStackTrace();
			}
		} else {

			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Listar Reserva", "Escolha um clinte."));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");

		}
	}

	public void inserirReserva() {
		try {
			this.restaurante = (Restaurante) UtilSession.getHttpSessionObject("restaurante");
			reserva.setRestaurante(restaurante);

			if (qtdReserva == 0) {
				FacesContext contexto = FacesContext.getCurrentInstance();
				contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inserir Reserva",
						"Informe pelo menos uma reserva."));
				org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
				return;
			}

			for (int i = 1; i <= qtdReserva; i++) {
				reserva = new Reserva();
				this.reserva.setData(data);
				this.reserva.setRestaurante(restaurante);
				this.f.inserirReserva(reserva);
			}

			reserva = new Reserva();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro", "Reserva cadastrada com sucesso!"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
			this.init();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inserir Reserva", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		}
	}

	public void removerReserva(Reserva r) {
		try {
			f.removerReserva(r);
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro", "Reserva removida com sucesso!"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void efetuarReserva() {
		try {
			reserva = f.buscarReserva(reserva.getIdReserva());
			cliente = (Cliente) UtilSession.getHttpSessionObject("cliente");
			this.reserva.setCliente(cliente);
			this.reserva.setStatus(Status.INDISPONIVEL);
			this.reserva.setQtdPessoas(qtdPessoas);
			f.alterarReserva(reserva);
			this.pedido = new Pedido();
			if(this.itens != null && this.itens.size() > 0){
				this.pedido.setCliente(cliente);
				this.pedido.setRestaurante(restaurante);
				this.pedido.setDataHora(reserva.getData());
				this.pedido.setIdReserva(reserva);
				this.pedido.setItens(itens);
				f.inserirPedido(pedido);
				
				for (int i = 0; i < itens.size(); i++) {

					itens.get(i).setPedido(pedido);

					f.inserirItem(itens.get(i));

				}
				
			}
			
			
			this.reserva = new Reserva();
			this.restaurante = new Restaurante();
			this.itens = new ArrayList<Item>();
			this.pedido = new Pedido();
			this.qtdPessoas = 0;
			this.qtdProduto = 0;
			
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmação", "Reserva efetuada com sucesso!"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
			org.primefaces.context.RequestContext.getCurrentInstance().update("formEfetuarReserva");

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alterar", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		}
	}

	public void cancelarReserva(Reserva r) {
		try {
			r.setStatus(Status.DISPONIVEL);
			r.setQtdPessoas(1);
			f.alterarReserva(r);
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmação", "Reserva cancelada com sucesso!"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
			org.primefaces.context.RequestContext.getCurrentInstance().update("tableReserva");

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alterar", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		}
	}

	public void mostrarMesas() {
		RequestContext.getCurrentInstance().openDialog("EscolherMesa");
	}

//	public List<Restaurante> completarRestaurante(String query) {
//		List<Restaurante> todosRestaurantes = f.buscarTodosRestaurante();
//		List<Restaurante> restaurantesFiltrados = new ArrayList<Restaurante>();
//
//		for (int i = 0; i < todosRestaurantes.size(); i++) {
//			Restaurante res = todosRestaurantes.get(i);
//			if (res.getNome().toLowerCase().startsWith(query)) {
//				restaurantesFiltrados.add(res);
//			}
//		}
//
//		return restaurantesFiltrados;
//	}

//	public List<String> completarTexto(String query) {
//		List<String> nomes = new ArrayList<String>();
//		restaurantes = f.buscarTodosRestaurante();
//
//		for (int i = 0; i < restaurantes.size(); i++) {
//			nomes.add(restaurantes.get(i).getNome());
//		}
//		return nomes;
//	}

	public void onRestauranteSelecionado(AjaxBehaviorEvent event) {
		try {
			UtilSession.setHttpSessionObject("restaurante", restaurante);
			produtosRestaurante = f.buscarProdutoRestaurante(restaurante);
			reservas = f.buscarReservaDisponivel(restaurante.getIdRestaurante());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(event.getObject());

	}

	public void onDatSelecionada(SelectEvent event) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String StringData = format.format(event.getObject());
		Date data;
		try {
			data = new SimpleDateFormat("yyyy-MM-dd").parse(StringData);
			reservas = f.buscarReservaDataRestaurante(data, restaurante.getIdRestaurante());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// public List<String> completarTextoProduto(String query) {
	//
	// if (restaurante != null) {
	// List<String> nomes = new ArrayList<String>();
	// try {
	// produtosRestaurante = f.buscarProdutoRestaurante(restaurante);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// for (int i = 0; i < produtosRestaurante.size(); i++) {
	// nomes.add(produtosRestaurante.get(i).getDescricao());
	// }
	// return nomes;
	// } else {
	// FacesContext contexto = FacesContext.getCurrentInstance();
	// contexto.addMessage(null,
	// new FacesMessage(FacesMessage.SEVERITY_ERROR, "Listar Produtos", "Escolha
	// um restaurante."));
	// org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
	// return null;
	// }
	//
	// }
	//
	//
	// <p:autoComplete id="Produto"
	// value="#{beanReserva.produtoSelecionado.descricao}"
	// forceSelection="true"
	// completeMethod="#{beanReserva.completarTextoProduto}">
	// <p:ajax event="itemSelect"
	// listener="#{beanReserva.onProdutoSelecionado}" />
	// </p:autoComplete>

	// public void onProdutoSelecionado(SelectEvent event) {
	// try {
	// produtoSelecionado = f.buscarProdutoDescricao((String) event.getObject(),
	// restaurante.getIdRestaurante());
	//
	// } catch (Exception e) {
	//
	// e.printStackTrace();
	// }
	// }

//	public List<Produto> completarProduto(String query) {
//		try {
//			produtosRestaurante = f.buscarProdutoRestaurante(restaurante);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		List<Produto> produtosFiltrados = new ArrayList<Produto>();
//
//		for (int i = 0; i < produtosRestaurante.size(); i++) {
//			Produto pro = produtosRestaurante.get(i);
//			if (pro.getDescricao().toLowerCase().startsWith(query)) {
//				produtosFiltrados.add(pro);
//			}
//		}
//
//		return produtosFiltrados;
//	}
//
//	public void onProdutoSelecionado(SelectEvent e) {
//		produtoSelecionado = (Produto) e.getObject();
//
//		// onClienteSelecionado
//	}

	public void excluirItem(Item item) {
		for (int i = 0; i < itens.size(); i++) {

			if (item.getProduto().getIdProduto() == itens.get(i).getProduto().getIdProduto()) {
				itens.remove(i);
			}

		}
	}

	public void inserirItem() {
		Item item = new Item();
		item.setProduto(produtoSelecionado);
		item.setQuantidade(qtdProduto);
		item.setValorItem(produtoSelecionado.getValorProduto() * qtdProduto);

		if (itens == null) {
			itens = new ArrayList<Item>();
		}
		if(itens.size() <=0){
			itens.add(item);
			return;
		}
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
			// item.setPedido(pedido);
		}
	}

	public void onRowEdit(RowEditEvent event) {
		try {

			this.setReserva((Reserva) event.getObject());

			f.alterarReserva(reserva);
			reserva = new Reserva();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro", "Reserva alterada com sucesso!"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alterar Reserva", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		}
		System.out.println("onrowedit");
	}

	public void onRowCancel(RowEditEvent event) {
		System.out.println("onrowcancel");
	}

	public SelectItem[] getStatusValues() {
		SelectItem[] items = new SelectItem[Status.values().length];
		int i = 0;
		for (Status s : Status.values()) {
			items[i++] = new SelectItem(s, s.toString());
		}
		return items;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

}
