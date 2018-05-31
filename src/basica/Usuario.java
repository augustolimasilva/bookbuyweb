package basica;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idUsuario;

	@Column(nullable = false, unique = true)
	private String login;

	@Column(nullable = false)
	private String senha;

	@OneToMany(mappedBy = "usuario")
	private List<Restaurante> restaurante;

	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int id) {
		this.idUsuario = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login.trim();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha.trim();
	}

	public List<Restaurante> getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(List<Restaurante> restaurantes) {
		restaurante = restaurantes;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
}