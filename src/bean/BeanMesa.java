package bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import basica.Mesa;
import basica.Restaurante;
import basica.Status;
import util.Fachada;
import util.UtilSession;

@ViewScoped
@ManagedBean
public class BeanMesa {

	Fachada f;
	private Restaurante restaurante;
	private List<Mesa> mesas;
	private Mesa mesa;
	private String statusMesa;

	@PostConstruct
	public void init() {
		try {
			this.f = Fachada.getInstancia();
			this.mesa = new Mesa();
			this.restaurante = new Restaurante();
			this.mesas = new ArrayList<Mesa>();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione um Restaurante!", e.getMessage()));
		}
	}

	public BeanMesa() {
		try {
			this.f = Fachada.getInstancia();
			this.mesa = new Mesa();
			this.mesas = new ArrayList<Mesa>();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione um Restaurante!", e.getMessage()));
		}
	}

	public void inserirMesa() {
		try {
			this.restaurante = (Restaurante) UtilSession.getHttpSessionObject("restaurante");
			this.mesa.setRestaurante(restaurante);
			if (statusMesa.equals("Disponivel")) {
				mesa.setStatus(Status.DISPONIVEL);
			} else if (statusMesa.equals("Indisponivel")) {
				mesa.setStatus(Status.INDISPONIVEL);
			}
			this.f.inserirMesa(mesa);
			mesa = new Mesa();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro", "Mesa cadastrada com sucesso!"));
			this.mesa = new Mesa();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inserir mesa", e.getMessage()));
		}
	}

	public void removerMesa(Mesa mesa) {
		try {
			this.restaurante = (Restaurante) UtilSession.getHttpSessionObject("restaurante");
			this.mesa.setRestaurante(restaurante);
			f.removerMesa(mesa);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluir", "Mesa excluída com sucesso!"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormRemoverMesa");
			org.primefaces.context.RequestContext.getCurrentInstance().update("listarMesa");
			this.mesa = new Mesa();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Remover mesa", e.getMessage()));
		}
	}

	public void alterarMesa() {
		try {
			this.restaurante = (Restaurante) UtilSession.getHttpSessionObject("restaurante");
			this.mesa.setRestaurante(restaurante);
			if (statusMesa.equals("Disponivel")) {
				mesa.setStatus(Status.DISPONIVEL);
			} else if (statusMesa.equals("Indisponivel")) {
				mesa.setStatus(Status.INDISPONIVEL);
			}
			f.alterarMesa(mesa);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterar", "Mesa atualizado com sucesso!"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("formAtualizar");
			// this.mesa = new Mesa();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alterar mesa", e.getMessage()));
		}
	}

	public void carregarRestaurante() throws Exception {
		this.restaurante = (Restaurante) UtilSession.getHttpSessionObject("restaurante");
		if (this.restaurante != null) {
			try {
				this.mesas = f.buscarMesaRestaurante(this.restaurante.getIdRestaurante());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Listar Mesas", "Escolha um restaurante!"));
		}
	}

	public void onRowEdit(RowEditEvent event) {
		try {
			this.setMesa((Mesa) event.getObject());
			this.alterarMesa();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alterar Mesa", e.getMessage()));
		}
		System.out.println("onrowedit");
	}

	public void onRowCancel(RowEditEvent event) {
		System.out.println("onrowcancel");
	}

	public void buscarMesaIdMesaRestaurante() {
		try {
			this.carregarRestaurante();
			this.mesa = f.buscarIdMesaRestaurante(mesa.getIdMesaRestaurante(),
					mesa.getRestaurante().getIdRestaurante());
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mesa não encontrada", e.getMessage()));
		}
	}

	public void buscarMesaRestaurante() {
		try {
			this.carregarRestaurante();
			mesas = f.buscarMesaRestaurante(mesa.getRestaurante().getIdRestaurante());
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mesas não encontrada", e.getMessage()));
		}
	}

	public void buscarMesaStatus() {
		try {
			this.carregarRestaurante();
			mesas = f.buscarMesaStatus(mesa.getStatus(), mesa.getRestaurante().getIdRestaurante());
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mesas não encontrada", e.getMessage()));
			this.mesa = new Mesa();
		}
	}

	public void buscarQtdLugar() {
		try {
			this.carregarRestaurante();
			mesas = f.buscarQtdLugar(mesa.getQtdLugar(), mesa.getRestaurante().getIdRestaurante());
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mesas não encontrada", e.getMessage()));
			this.mesa = new Mesa();
		}
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public List<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}

	public String getStatusMesa() {
		return statusMesa;
	}

	public void setStatusMesa(String statusMesa) {
		this.statusMesa = statusMesa;
	}
}