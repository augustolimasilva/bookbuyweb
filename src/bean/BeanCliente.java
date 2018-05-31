package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import basica.Cliente;
import basica.Restaurante;
import util.Fachada;
import util.UtilSession;

@ManagedBean(name = "beanCliente")
@ViewScoped
public class BeanCliente implements Serializable {

	private static final long serialVersionUID = -6932602038416909748L;
	Fachada f;
	private Cliente cliente;
	private List<Cliente> clientes;
	private List<Restaurante> restaurantes;

	@PostConstruct
	public void init() {
		this.f = Fachada.getInstancia();
		this.clientes = new ArrayList<Cliente>();
		this.clientes = this.f.buscarTodosClientes();
		this.cliente = new Cliente();
		if (UtilSession.getHttpSessionObject("cliente") != null) {
			this.cliente = (Cliente) UtilSession.getHttpSessionObject("cliente");
		}
		this.restaurantes = new ArrayList<Restaurante>();
		this.restaurantes = f.buscarTodosRestaurante();
	}

	public BeanCliente() {
		this.f = Fachada.getInstancia();
		this.clientes = new ArrayList<Cliente>();
		this.clientes = this.f.buscarTodosClientes();
		this.cliente = new Cliente();
		if (UtilSession.getHttpSessionObject("cliente") != null) {
			this.cliente = (Cliente) UtilSession.getHttpSessionObject("cliente");
		}
		this.restaurantes = new ArrayList<Restaurante>();
		this.restaurantes = f.buscarTodosRestaurante();
	}

	public void inserirCliente() {
		try {
			this.f.inserirCliente(this.cliente);
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro", "Cliente cadastrado com sucesso"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormCadastroCliente");
			this.cliente = new Cliente();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cadastro", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		}
	}

	public String removerCliente() {
		try {
			this.f.removerCliente(this.cliente);
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluir", "Cliente excluído com sucesso"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormRemoverCliente");
			this.cliente = new Cliente();
			return "Login";
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Excluir", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
			return "";
		}
	}

	public void alterarCliente() {
		try {
			this.f.alterarCliente(cliente);
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterar", "Cliente atualizado com sucesso!"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
			org.primefaces.context.RequestContext.getCurrentInstance().update("formAtualizar");
			this.cliente = new Cliente();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alterar", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		}
	}

	public void buscarCliente() {
		try {
			this.cliente = f.buscarClienteEmail(cliente.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Excluir", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
			this.cliente = new Cliente();
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}

	// public void buscarClienteTelefone() {
	// try {
	// cliente = f.buscarClienteTelefone(cliente.getTelefone());
	// } catch (Exception e) {
	// e.printStackTrace();
	// FacesContext contexto = FacesContext.getCurrentInstance();
	// contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	// "Excluir", e.getMessage()));
	// org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
	// this.cliente = new Cliente();
	// }
	// }
}
