package basica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Mesa {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idMesa;

	@Column(nullable = false)
	private int idMesaRestaurante;

	@ManyToOne
	@JoinColumn(name = "idRestaurante", foreignKey = @ForeignKey(name = "fk_mesa_restaurante") )
	private Restaurante restaurante;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	@Column(nullable = false)
	private int qtdLugar;

	@OneToMany(mappedBy = "mesa")
	private List<Pedido> pedidos;

	public Mesa() {
		//this.situacao = Situacao.ATIVO;
		this.restaurante = new Restaurante();
		//this.status = Status.DISPONIVEL;
		this.pedidos = new ArrayList<Pedido>();

	}

	public int getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getQtdLugar() {
		return qtdLugar;
	}

	public void setQtdLugar(int qtdLugar) {
		this.qtdLugar = qtdLugar;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public int getIdMesaRestaurante() {
		return idMesaRestaurante;
	}

	public void setIdMesaRestaurante(int idMesaRestaurante) {
		this.idMesaRestaurante = idMesaRestaurante;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
}
