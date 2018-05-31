package basica;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import util.Fachada;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPedido;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHora;

	@Column
	private String tempoEstimado;

	@ManyToOne
	@JoinColumn(name = "idCliente", foreignKey = @ForeignKey(name = "fk_pedido_cliente") )
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "idRestaurante", foreignKey = @ForeignKey(name = "fk_pedido_restaurante") )
	private Restaurante restaurante;

	// @OneToOne(mappedBy = "pedido")
	// private Pagamento pagamento;

	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	@OneToMany(mappedBy = "pedido")
	private List<Item> itens;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToOne
	@JoinColumn(name = "idMesa", foreignKey = @ForeignKey(name = "fk_pedido_mesa") )
	private Mesa mesa;

	@OneToOne
	@JoinColumn(name = "idReserva", foreignKey = @ForeignKey(name = "fk_pedido_reserva") )
	private Reserva idReserva;
	
	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	//
	// public Pagamento getPagamento() {
	// return pagamento;
	// }
	//
	// public void setPagamento(Pagamento pagamento) {
	// this.pagamento = pagamento;
	// }

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public String getTempoEstimado() {
		return tempoEstimado;
	}

	public void setTempoEstimado(String tempoEstimado) {
		this.tempoEstimado = tempoEstimado;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Reserva getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Reserva idReserva) {
		this.idReserva = idReserva;
	}

	public float totalPedido() throws Exception {
		Fachada f = Fachada.getInstancia();
		List<Item> itens = new ArrayList<Item>();
		float total = 0;
		itens = f.buscarItensPedido(this.idPedido);

		for (int i = 0; i < itens.size(); i++) {
			total += itens.get(i).getValorItem();
		}
		return total;
	}
}
