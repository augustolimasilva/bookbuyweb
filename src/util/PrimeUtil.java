package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class PrimeUtil {

	public void messageInfo(String cabecalho, String corpo) {
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, cabecalho, corpo));
	}

	public void messageErro(String cabecalho, String corpo) {
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, cabecalho, corpo));
	}

	public void messageAviso(String cabecalho, String corpo) {
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, cabecalho, corpo));
	}

	public void messageFatal(String cabecalho, String corpo) {
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, cabecalho, corpo));
	}

	public void update(String componente) {
		org.primefaces.context.RequestContext.getCurrentInstance().update(componente);
	}

	public void execute(String componente) {
		org.primefaces.context.RequestContext.getCurrentInstance().execute(componente);
	}

}