package basica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class TipoRestaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idTipo;

	@Column(nullable = false)
	private String descricao;

	@ManyToMany
	@JoinTable(name = "restaurante_Tipo", joinColumns = @JoinColumn(name = "idTipo") , inverseJoinColumns = @JoinColumn(name = "idRestaurante") )
	private List<Restaurante> restaurantes;

	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao.trim().toUpperCase();
	}

	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public TipoRestaurante() {
		this.restaurantes = new ArrayList<Restaurante>();
	}
}
