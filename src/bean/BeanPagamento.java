package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import basica.FormaPagamento;
import basica.Pagamento;
import basica.Pedido;
import util.Fachada;
import util.UtilSession;

@SessionScoped
@ManagedBean
public class BeanPagamento {

	Fachada f;
	private Pagamento pagamento;
	private Pedido pedido;
	private List<Pagamento> pagamentos;
	private String formaPagamento;

	@PostConstruct
	public void init() {
		try {
			this.f = Fachada.getInstancia();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BeanPagamento() {
		try {
			this.f = Fachada.getInstancia();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro interno do sistema!", e.getMessage()));
		}
	}

	public void carregarPedido() {
		try {
			this.pedido = (Pedido) UtilSession.getHttpSessionObject("pedido");
			Date c = new Date();
			this.pagamento = new Pagamento();
			this.pagamento.setDataHoraPagto(c);
			this.pagamento.setPedido(this.pedido);
			this.pagamento.setValor(this.pedido.totalPedido());
			this.pagamentos = new ArrayList<Pagamento>();
			this.pagamentos = f.buscarTodosPagamento();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao carregar o pedido!", e.getMessage()));
		}
	}

	public void inserirPagamento() {
		try {
			if (formaPagamento.equals("Credito")) {
				pagamento.setFormaPagamento(FormaPagamento.CREDITO);
			} else if (formaPagamento.equals("Debito")) {
				pagamento.setFormaPagamento(FormaPagamento.DEBITO);
			} else if (formaPagamento.equals("Dinheiro")) {
				pagamento.setFormaPagamento(FormaPagamento.DINHEIRO);
			}
			this.f.inserirPagamento(pagamento);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro", "Pagamento efetuado!"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inserir pagamento", e.getMessage()));
		}
	}

	public void removerPagamento() {
		try {
			this.pedido = (Pedido) UtilSession.getHttpSessionObject("pedido");
			this.pagamento.setPedido(this.pedido);
			this.pagamento.setValor(this.pedido.totalPedido());
			this.f.removerPagamento(pagamento);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluir", "Pagamento excluída com sucesso!"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormRemoverPagamento");
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Remover pagamento", e.getMessage()));
		}
	}

	public void alterarPagamento() {
		try {
			this.pedido = (Pedido) UtilSession.getHttpSessionObject("pedido");
			this.pagamento.setPedido(this.pedido);
			this.pagamento.setValor(this.pedido.totalPedido());
			if (formaPagamento.equals("Credito")) {
				pagamento.setFormaPagamento(FormaPagamento.CREDITO);
			} else if (formaPagamento.equals("Debito")) {
				pagamento.setFormaPagamento(FormaPagamento.DEBITO);
			}
			this.f.alterarPagamento(pagamento);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterar", "Pagamento atualizado com sucesso!"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("formAtualizar");
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alterar mesa", e.getMessage()));
		}
	}

	public void buscarPagamento() {
		try {
			pagamento = this.f.buscarPagamento(pagamento);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mesa não encontrada", e.getMessage()));
		}
	}

	public void buscarTodosPagamento() {
		try {
			pagamentos = this.f.buscarTodosPagamento();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mesa não encontrada", e.getMessage()));
		}
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
}
