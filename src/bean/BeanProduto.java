package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import basica.Produto;
import basica.Restaurante;
import util.Fachada;
import util.UtilSession;

@SessionScoped
@ManagedBean(name = "beanProduto")
public class BeanProduto implements Serializable {


	private static final long serialVersionUID = -7063247472052742586L;
	Fachada f;
	private Produto produto;
	private Restaurante restaurante;
	private List<Produto> produtos;

	public BeanProduto() {
		f = Fachada.getInstancia();
		this.produto = new Produto();
		this.restaurante = new Restaurante();
		this.produtos = new ArrayList<Produto>();

	}

	@PostConstruct
	public void init() {

		// this.primeUtil = new PrimeUtil();
	}

	public void pegarRestaurante() {
		restaurante = (Restaurante) UtilSession.getHttpSessionObject("restaurante");
		if (restaurante != null) {
			try {
				produtos = f.buscarProdutoRestaurante(restaurante);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
	
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Listar Produtos", "Escolha um restaurante."));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
	
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void removerProduto(Produto p) {
		try {
			f.removerProduto(p);
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro", "Produto removido com sucesso!"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void inserirProduto() throws Exception {
		try {
 			this.restaurante = (Restaurante) UtilSession.getHttpSessionObject("restaurante");
			produto.setRestaurante(restaurante);
			f.inserirProduto(produto);
			produto = new Produto();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro", "Produto cadastrada com sucesso!"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inserir Produto", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		}

	}

	public void onRowEdit(RowEditEvent event) {
		try {

			this.setProduto((Produto) event.getObject());

			f.alterarProduto(produto);
			produto = new Produto();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro", "Produto alterado com sucesso!"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alterar Produto", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		}
		System.out.println("onrowedit");
	}

	public void onRowCancel(RowEditEvent event) {
		System.out.println("onrowcancel");
	}

}
