package bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import basica.Cliente;
import basica.Usuario;
import util.Fachada;
import util.MandarEmail;
import util.UtilSession;
import util.Validacao;

@ManagedBean(name = "beanLogin")
@ViewScoped
public class BeanLogin implements Serializable {

	private static final long serialVersionUID = -6932602038416909748L;
	Fachada f;
	private MandarEmail enviarEmail;
	private String login;
	private String senha;
	private String email;
	private String tipo;
	private Usuario usuario;
	private Cliente cliente;
	public boolean logado = false;

	@PostConstruct
	public void init() {
		this.f = Fachada.getInstancia();
		this.enviarEmail = new MandarEmail();
		this.usuario = new Usuario();
		this.cliente = new Cliente();
	}

	public BeanLogin() {
		this.f = Fachada.getInstancia();
		this.enviarEmail = new MandarEmail();
		this.usuario = new Usuario();
		this.cliente = new Cliente();
	}

	public void esqueceuSenha() {
		try {
			Validacao.isEmailValid(this.email);
//			if (this.tipo.equals("Restaurante")) {
//
//			} else if (this.tipo.equals("Cliente")) {
				Cliente cli = new Cliente();
				cli = this.f.buscarClienteEmail(this.email);
				this.enviarEmail.enviarEmail(cli.getEmail(), cli.getSenha());
//			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Esqueceu a Senha", "Senha enviada para o email informado!"));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Esqueceu a Senha", e.getMessage()));
		}
	}

	public String login() throws Exception {
		try {
			if (login.equals("admin") && senha.equals("admin")) {
				usuario = new Usuario();
				UtilSession.setHttpSessionObject("usuario", usuario);
				return "MenuAdmin";
			}
			if (tipo.equals("Restaurante")) {
				if (f.validarLoginUsuario(login, senha)) {
					logado = true;
					usuario = f.buscarUsuarioLogin(login);
					UtilSession.setHttpSessionObject("usuario", usuario);
					return "MenuRestaurante";
				}
			} else if (tipo.equals("Cliente")) {
				f.validarLoginCliente(login, senha);
				logado = true;
				cliente = f.buscarClienteLogin(login);
				UtilSession.setHttpSessionObject("cliente", cliente);
				return "MenuCliente";
			}
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
			return "";
		}
	}

	public String logout() {
		HttpSession session = UtilSession.getHttpSession();
		session.invalidate();
		logado = false;
		return "Login";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
