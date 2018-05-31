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

import basica.TipoRestaurante;
import util.Fachada;

@SessionScoped
@ManagedBean(name = "beanTipoRestaurante")
public class BeanTipoRestaurante implements Serializable {

	private static final long serialVersionUID = 1L;
	private Fachada f;
	private TipoRestaurante tipoRestaurante;
	private List<TipoRestaurante> tipoRestaurantes;

	@PostConstruct
	public void init() {
		this.f = Fachada.getInstancia();
		this.tipoRestaurante = new TipoRestaurante();
		this.tipoRestaurantes = new ArrayList<TipoRestaurante>();
	}

	public void inserirTipoRestaurante() throws Exception {
		try {
			this.f.inserirTipoRestaurante(this.tipoRestaurante);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro", "Tipo cadastrado com sucesso!"));
			this.tipoRestaurante = new TipoRestaurante();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inserir tipo", e.getMessage()));
		}
	}

	public void alterarTipoRestaurante() {
		try {
			this.f.alterarTipoRestaurante(this.tipoRestaurante);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterar",
					"Tipo Restaurante atualizado com sucesso!"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("formAtualizar");
			this.tipoRestaurante = new TipoRestaurante();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alterar Tipo Restaurante", e.getMessage()));
		}
	}

	public void removerTipoRestaurante(TipoRestaurante tipoRestaurante) {
		try {
			this.f.removerTipoRestaurante(tipoRestaurante);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluir", "Tipo Restaurante excluída com sucesso!"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("formRemoverTipoRestaurante");
			org.primefaces.context.RequestContext.getCurrentInstance().update("listarTipoRestaurante");
			this.tipoRestaurante = new TipoRestaurante();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Remover Tipo Restaurante", e.getMessage()));
		}
	}

	public void onRowEdit(RowEditEvent event) {
		try {
			this.setTipoRestaurante((TipoRestaurante) event.getObject());
			this.alterarTipoRestaurante();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alterar Tipo Restaurante", e.getMessage()));
		}
		System.out.println("onrowedit");
	}

	public void onRowCancel(RowEditEvent event) {
		System.out.println("onrowcancel");
	}

	public void buscarTipoRestaurante() {
		try {
			this.tipoRestaurante = this.f.buscarTipoRestaurante(this.tipoRestaurante);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipo Restaurante não encontrado", e.getMessage()));
		}
	}

	public void buscarTodosTipoRestaurante() {
		try {
			this.tipoRestaurantes = this.f.buscarTodosTipoRestaurante();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Buscar Tipo Restaurante", e.getMessage()));
		}
	}

	public TipoRestaurante getTipoRestaurante() {
		return tipoRestaurante;
	}

	public void setTipoRestaurante(TipoRestaurante tipoRestaurante) {
		this.tipoRestaurante = tipoRestaurante;
	}

	public List<TipoRestaurante> getTipoRestaurantes() {
		return tipoRestaurantes;
	}

	public void setTipoRestaurantes(List<TipoRestaurante> tipoRestaurantes) {
		this.tipoRestaurantes = tipoRestaurantes;
	}
}
