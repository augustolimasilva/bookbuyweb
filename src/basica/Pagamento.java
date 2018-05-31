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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPagamento;

	@Enumerated(EnumType.STRING)
	private FormaPagamento formaPagamento;

	@Temporal(TemporalType.DATE)
	private Date data;

	@OneToOne
	@JoinColumn(name = "idPedido", foreignKey = @ForeignKey(name = "fk_pedido_pagamento") )
	private Pedido pedido;

	@Column(nullable = false)
	private float valor;

	@Enumerated(EnumType.STRING)
	private Situacao situacao;

	public Pagamento() {
		// this.situacao = Situacao.ATIVO;
		// this.formaPagamento = FormaPagamento.CREDITO;
		this.data = new Date();
		this.pedido = new Pedido();
	}

	public int getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Date getDataHoraPagto() {
		return data;
	}

	public void setDataHoraPagto(Date dataHoraPagto) {
		this.data = dataHoraPagto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
}