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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Promocao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPromocao;

	@Temporal(TemporalType.DATE)
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	private Date dataFinal;

	@Enumerated(EnumType.STRING)
	private TipoPromo tipo;

	@OneToMany(mappedBy = "promocao")
	private List<Item> itens;

	@Column(nullable = false)
	private float valor;

	@Column(nullable = false)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "idRestaurante", foreignKey = @ForeignKey(name = "fk_promocao_restaurante") )
	private Restaurante restaurante;

	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	public Promocao() {
		// this.situacao = Situacao.ATIVO;
		 this.dataInicio = new Date();
		 this.dataFinal = new Date();
		// this.tipo = TipoPromo.NUMERICO;
		this.itens = new ArrayList<Item>();
		this.restaurante = new Restaurante();
	}

	public int getIdPromocao() {
		return idPromocao;
	}

	public void setIdPromocao(int idPromocao) {
		this.idPromocao = idPromocao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public TipoPromo getTipo() {
		return tipo;
	}

	public void setTipo(TipoPromo tipo) {
		this.tipo = tipo;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao.trim();
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
}
