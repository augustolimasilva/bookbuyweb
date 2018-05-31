package basica;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Rate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idRate;

	@ManyToOne
	@JoinColumn(name = "idRestaurante", foreignKey = @ForeignKey(name = "fk_rate_restaurante") )
	private Restaurante restaurante;

	private float rate;

	@ManyToOne
	@JoinColumn(name = "idCliente", foreignKey = @ForeignKey(name = "fk_rate_cliente") )
	private Cliente cliente;

	public int getIdRate() {
		return idRate;
	}

	public void setIdRate(int idRate) {
		this.idRate = idRate;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
