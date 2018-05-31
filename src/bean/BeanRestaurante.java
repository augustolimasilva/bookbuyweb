package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import basica.Restaurante;
import basica.TipoRestaurante;
import basica.Usuario;
import util.Fachada;
import util.UtilSession;

@SessionScoped
@ManagedBean
public class BeanRestaurante implements Serializable {

	private static final long serialVersionUID = 1L;
	Fachada f = Fachada.getInstancia();
	private Restaurante restaurante = new Restaurante();
	// private Endereco endereco = new Endereco();
	// private NegocioRestaurante negocioRestaurante;
	private List<TipoRestaurante> tipos;
	private List<TipoRestaurante> tipoSelecionado;
	private List<Restaurante> restaurantes = new ArrayList<Restaurante>();
	private List<Restaurante> filterRestaurantes = new ArrayList<Restaurante>();
	Usuario usuario = new Usuario();
	private MapModel locais;
	private Marker marcacao;
	private String posicionamento = "41.850033, -87.6500523";
	private String cnpj;
	private boolean selecionou = false;

	@PostConstruct
	public void init() {
		this.locais = new DefaultMapModel();
		tipos = f.buscarTodosTipoRestaurante();

		// tipoSelecionado = new ArrayList<TipoRestaurante>();
		usuario = (Usuario) UtilSession.getHttpSessionObject("usuario");
		if (usuario != null) {
			try {
				restaurantes = f.listarRestaurantesUsuario(usuario);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			this.restaurantes = f.buscarTodosRestaurante();

		}
	}

	public Integer calcularRate(Restaurante r) {
		return f.calcularRate(f.buscarRateRestaurante(r));
	}

	public BeanRestaurante() {
		restaurante = new Restaurante();
		// endereco = new Endereco();
	}

	public void inserirRestaurante() throws Exception {
		try {

			restaurante.setUsuario(usuario);
			// restaurante.setEndereco(endereco);
			restaurante.setTipos(tipoSelecionado);
			f.inserirRestaurante(this.restaurante);
			init();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro", "Restaurante cadastrado com sucesso"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
			this.restaurante = new Restaurante();
			// this.endereco = new Endereco();
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormCadastroRestaurante");

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cadastro", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		}
	}

	public void buscarRestauranteCnpj() {
		try {
			restaurante = f.buscarRestauranteCnpj(restaurante.getCnpj());
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pesquisar", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		}
	}

	public void removerRestaurante() throws Exception {

		try {

			if (!selecionou) {
				throw new Exception("Escolha um restaurante.");
			} else {

				if (!restaurante.getCnpj().trim().equals(this.cnpj.trim())) {
					throw new Exception("CNPJ não confere.");
				}
				f.removerRestauranteCnpj(this.restaurante.getCnpj());
				FacesContext contexto = FacesContext.getCurrentInstance();
				contexto.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluir", "Restaurante excluído com sucesso"));
				org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
				org.primefaces.context.RequestContext.getCurrentInstance().update("idFormRemoverRestaurante");
				ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
				init();
				context.redirect("MenuRestaurante.xhtml");
			}

		} catch (Exception e) {

			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Excluir", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		}
	}

	public void alterarRestaurante() throws Exception {
		try {

			if (!selecionou) {
				throw new Exception("Escolha um restaurante.");
			} else {
				restaurante.setTipos(tipoSelecionado);
				f.alterarRestaurante(restaurante);
				restaurante = new Restaurante();
				// endereco = new Endereco();
				FacesContext contexto = FacesContext.getCurrentInstance();
				contexto.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterar", "Restaurante atualizado com sucesso"));
				org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
				this.restaurante = new Restaurante();
				org.primefaces.context.RequestContext.getCurrentInstance().update("formAtualizar");
			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alterar", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		}
	}

	public Restaurante pesquisar(int id) {
		try {
			return f.buscarRestaurante(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void escolherRestaurante(AjaxBehaviorEvent event) {
		try {

			restaurante = f.buscarRestauranteNome(restaurante.getNome());
			UtilSession.setHttpSessionObject("restaurante", restaurante);
			this.cnpj = restaurante.getCnpj();
			selecionou = true;
			// RequestContext.getCurrentInstance().update("foo:bar");
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			init();
			context.redirect("MenuRestaurante.xhtml");

		} catch (Exception e) {
			selecionou = false;
			restaurante = new Restaurante();
			UtilSession.setHttpSessionObject("restaurante", restaurante);
			e.printStackTrace();
		}
	}

	public void pontoSelecionado(PointSelectEvent event) {
		LatLng latituteLongitude = event.getLatLng();
		restaurante.getEndereco().setLatitude(String.valueOf(latituteLongitude.getLat()));
		restaurante.getEndereco().setLongitude(String.valueOf(latituteLongitude.getLng()));
		marcacao = new Marker(new LatLng(latituteLongitude.getLat(), latituteLongitude.getLng()));
		locais.getMarkers().clear();
		locais.addOverlay(marcacao);
	}

	public void onGeocode(GeocodeEvent event) {
		List<GeocodeResult> results = event.getResults();

		if (results != null && !results.isEmpty()) {
			LatLng center = results.get(0).getLatLng();
			posicionamento = center.getLat() + "," + center.getLng();
			restaurante.getEndereco().setLatitude(String.valueOf(center.getLat()));
			restaurante.getEndereco().setLongitude(String.valueOf(center.getLng()));

			for (int i = 0; i < results.size(); i++) {
				GeocodeResult result = results.get(i);
				locais.getMarkers().clear();
				locais.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
			}
		}
	}

	public void avaliar() {

	}

	public List<TipoRestaurante> getTipoSelecionado() {
		return tipoSelecionado;
	}

	public void setTipoSelecionado(List<TipoRestaurante> tipoSelecionado) {
		this.tipoSelecionado = tipoSelecionado;
	}

	public List<TipoRestaurante> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoRestaurante> tipos) {
		this.tipos = tipos;
	}

	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}

	public List<Restaurante> getfilterRestaurantes() {
		return filterRestaurantes;
	}

	public void setfilterRestaurantes(List<Restaurante> filterRestaurantes) {
		this.filterRestaurantes = filterRestaurantes;
	}

	public MapModel getLocais() {
		return locais;
	}

	public void setLocais(MapModel locais) {
		this.locais = locais;
	}

	public Marker getMarcacao() {
		return marcacao;
	}

	public void setMarcacao(Marker marcacao) {
		this.marcacao = marcacao;
	}

	public String getPosicionamento() {
		return posicionamento;
	}

	public void setPosicionamento(String posicionamento) {
		this.posicionamento = posicionamento;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	// public Endereco getEndereco() {
	// return endereco;
	// }
	//
	// public void setEndereco(Endereco endereco) {
	// this.endereco = endereco;
	// }
}