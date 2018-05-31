package util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MandarEmail {

	private String servidor;
	private String servidorPorta;
	private String remetente;
	private String senha;
	private String assunto;
	private String mensagem;

	public MandarEmail() {
		this.servidor = "smtp.gmail.com";
		this.servidorPorta = "465";
		this.remetente = "bookbuysystem@gmail.com";
		this.senha = "a123456*";
		this.assunto = "Senha para acesso do BookBuy";
		this.mensagem = "Sua senha é: ";
	}

	public void enviarEmail(String para, String senhaCliente) throws MessagingException, Exception {
		// Propriedades Necessarias
		Properties props = new Properties();
		// Modo debug para verificar os passos do envio
		props.put("mail.debug", "true");
		// Servidor SMTP do GMAIL
		props.put("mail.smtp.host", this.servidor);
		// Porta
		props.put("mail.smtp.port", servidorPorta);
		// Necessario autenticacao
		props.put("mail.smtp.auth", "true");
		// Liga o SSL
		props.put("mail.smtp.ssl.enable", "true");

		// Cria a sessao
		Session session = Session.getInstance(props, new AutenticacaoEmail(this.remetente, this.senha));

		// Pega a sessao com usuario e senha
		MimeMessage msg = new MimeMessage(session);
		// Coloca O corpo do titulo
		msg.setText(this.mensagem + senhaCliente);
		// Coloca o assunto
		msg.setSubject(this.assunto);
		// Coloca quem enviou
		msg.setFrom(new InternetAddress(this.remetente));
		// Coloca para quem sera enviado
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(para));

		// Envia a mensagem
		Transport.send(msg);
	}

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public String getServidorPorta() {
		return servidorPorta;
	}

	public void setServidorPorta(String servidorPorta) {
		this.servidorPorta = servidorPorta;
	}

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
