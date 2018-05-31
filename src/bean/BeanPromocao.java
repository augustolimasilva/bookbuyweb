package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import basica.Promocao;
import basica.Restaurante;
import basica.TipoPromo;
import util.Fachada;
import util.UtilSession;

@ViewScoped
@ManagedBean
public class BeanPromocao {

	Fachada f;
	private Promocao promocao;
	private List<Promocao> promocoes;
	private String tipoPromo;
	private Restaurante restaurante;

	@PostConstruct
	public void init() {
		try {
			this.f = Fachada.getInstancia();
			this.promocao = new Promocao();
			this.restaurante = new Restaurante();
			this.promocoes = new ArrayList<Promocao>();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione um Restaurante!", e.getMessage()));
		}
	}

	public BeanPromocao() {
		try {
			this.f = Fachada.getInstancia();
			this.promocao = new Promocao();
			this.promocoes = new ArrayList<Promocao>();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione um Restaurante!", e.getMessage()));
		}
	}

	public void carregarRestaurante() throws Exception {
		this.restaurante = (Restaurante) UtilSession.getHttpSessionObject("restaurante");
		if (this.restaurante != null) {
			try {
				promocoes = f.buscarPromoRestaurante(restaurante.getIdRestaurante());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Listar Promoção", "Escolha um restaurante!"));
		}
	}

	public void inserirPromocao() {
		try {
			this.restaurante = (Restaurante) UtilSession.getHttpSessionObject("restaurante");
			this.promocao.setRestaurante(restaurante);
			if (tipoPromo.equals("Percentual")) {
				this.promocao.setTipo(TipoPromo.PERCENTUAL);
			} else if (tipoPromo.equals("Numerico")) {
				this.promocao.setTipo(TipoPromo.NUMERICO);
			}
			this.f.inserirPromocao(this.promocao);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro", "Promoção cadastrada com sucesso!"));
			this.promocao = new Promocao();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inserir Promoção", e.getMessage()));
		}
	}

	public void alterarPromocao() {
		try {
			this.restaurante = (Restaurante) UtilSession.getHttpSessionObject("restaurante");
			this.promocao.setRestaurante(restaurante);
			if (tipoPromo.equals("Percentual")) {
				promocao.setTipo(TipoPromo.PERCENTUAL);
			} else if (tipoPromo.equals("Numerico")) {
				promocao.setTipo(TipoPromo.NUMERICO);
			}
			this.f.alterarPromocao(this.promocao);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterar", "Promoção atualizado com sucesso!"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("formAtualizar");
			this.promocao = new Promocao();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alterar Promoção", e.getMessage()));
		}
	}

	public void removerPromocao(Promocao promocao) {
		try {
			this.restaurante = (Restaurante) UtilSession.getHttpSessionObject("restaurante");
			promocao.setRestaurante(restaurante);
			this.f.removerPromocao(promocao);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluir", "Promoção excluída com sucesso!"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormRemoverPromocao");
			org.primefaces.context.RequestContext.getCurrentInstance().update("listarPromocao");
			this.promocao = new Promocao();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Remover promoção", e.getMessage()));
		}
	}

	public void onRowEdit(RowEditEvent event) {
		try {
			this.setPromocao((Promocao) event.getObject());
			this.alterarPromocao();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alterar Promoção", e.getMessage()));
		}
		System.out.println("onrowedit");
	}

	public void onRowCancel(RowEditEvent event) {
		System.out.println("onrowcancel");
	}

	public void buscarPromocao() {
		try {
			this.restaurante = (Restaurante) UtilSession.getHttpSessionObject("restaurante");
			this.promocao.setRestaurante(restaurante);
			this.promocao = this.f.buscarPromocao(promocao.getIdPromocao(), promocao.getRestaurante().getIdRestaurante());
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Promoção não encontrada", e.getMessage()));
		}
	}

	public void buscarTodasPromocao() {
		try {
			this.carregarRestaurante();
			this.promocoes = this.f.buscarPromoRestaurante(restaurante.getIdRestaurante());
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Buscar Promoção", e.getMessage()));
		}
	}

	public void buscarDataPromocao(Date dataInicio, Date dataFinal) {
		try {
			this.carregarRestaurante();
			this.promocoes = this.f.buscarDataPromocao(promocao.getDataInicio(), promocao.getDataFinal(),
					promocao.getRestaurante().getIdRestaurante());
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Promoção não encontrada", e.getMessage()));
		}
	}

	public void buscarTipoPromocao() {
		try {
			this.carregarRestaurante();
			this.promocoes = this.f.buscarTipoPromocao(promocao.getTipo(), promocao.getRestaurante().getIdRestaurante());
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Promoção não encontrada", e.getMessage()));
		}
	}

	// public void buscarDescricaoPromocao() {
	// try {
	// this.carregarRestaurante();
	// promocoes = this.f.buscarDescricaoParcilPromocao(promocao.getDescricao(),
	// promocao.getRestaurante().getIdRestaurante());
	// } catch (Exception e) {
	// e.printStackTrace();
	// FacesContext.getCurrentInstance().addMessage(null,
	// new FacesMessage(FacesMessage.SEVERITY_ERROR, "Promoção não encontrada",
	// e.getMessage()));
	// }
	// }

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}

	public List<Promocao> getPromocoes() {
		return promocoes;
	}

	public void setPromocoes(List<Promocao> promocoes) {
		this.promocoes = promocoes;
	}

	public String getTipoPromo() {
		return tipoPromo;
	}

	public void setTipoPromo(String tipoPromo) {
		this.tipoPromo = tipoPromo;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
}
