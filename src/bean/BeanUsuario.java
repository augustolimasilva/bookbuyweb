package bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import basica.Usuario;
import util.Fachada;
import util.UtilSession;

@ManagedBean(name = "beanUsuario")
@ViewScoped
public class BeanUsuario implements Serializable {

	private static final long serialVersionUID = -6932602038416909748L;
	Fachada f;
	private Usuario usuario;
	String senha;
	public boolean logado = false;

	@PostConstruct
	public void init() {
		try {
			f = Fachada.getInstancia();
			usuario = (Usuario) UtilSession.getHttpSessionObject("usuario");
			if (usuario != null) {
				senha = usuario.getSenha();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BeanUsuario() {
		this.usuario = new Usuario();
	}

	public void inserirUsuario() {
		try {
			f.inserirUsuario(usuario);
			this.usuario = new Usuario();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserir Usuário", "Usuário cadastrado com sucesso!"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("formUsuario");
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inserir Usuário", e.getMessage()));
		}
	}

	public void alterarUsuario() {
		try {
			f.alterarUsuario(usuario);
			usuario = new Usuario();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterar", "Usuario atualizado com sucesso"));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
			this.usuario = new Usuario();
			org.primefaces.context.RequestContext.getCurrentInstance().update("formAtualizar");
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alterar", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		}
	}

	public void removerUsuario() {
		try {
			if (!usuario.getSenha().equals(this.senha)) {
				throw new Exception("Senha não confere.");
			} else {
				f.removerUsuario(usuario);
				FacesContext contexto = FacesContext.getCurrentInstance();
				contexto.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluir", "Usuario removido com sucesso!"));
				org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
				// return "Login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext contexto = FacesContext.getCurrentInstance();
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Excluir", e.getMessage()));
			org.primefaces.context.RequestContext.getCurrentInstance().update("idFormMsg");
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
