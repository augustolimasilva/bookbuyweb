package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

// Classe necessaria para passar usuario e senha caso precisa de autenticacao
public class AutenticacaoEmail extends Authenticator {

	public String username = null;
	public String password = null;

	// Recebendo usuario e senha e guardando nas variaveis
	public AutenticacaoEmail(String user, String pwd) {
		username = user;
		password = pwd;
	}

	// Esse é o metodo usado para enviar usuario e senha
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}
