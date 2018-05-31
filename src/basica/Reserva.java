package basica;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idReserva;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHora;


	@ManyToOne
	@JoinColumn(name = "idCliente", foreignKey = @ForeignKey(name = "fk_reserva_cliente") )
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "idRestaurante", foreignKey = @ForeignKey(name = "fk_reserva_restaurante") )
	private Restaurante restaurante;

	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	@Column(nullable = false)
	private int qtdPessoas;

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public Date getData() {
		return dataHora;
	}

	public void setData(Date dataHora) {
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getQtdPessoas() {
		return qtdPessoas;
	}

	public void setQtdPessoas(int qtdPessoas) {
		this.qtdPessoas = qtdPessoas;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	
	

}