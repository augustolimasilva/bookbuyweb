package basica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PedidoProduto")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idItem;

	@Column(nullable = false)
	private int quantidade;

	@Column(nullable = false)
	private float valorItem;

	@ManyToOne
	@JoinColumn(name = "idPedido", foreignKey = @ForeignKey(name = "fk_item_pedido") )
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "idProduto", foreignKey = @ForeignKey(name = "fk_item_produto") )
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "idPromocao", foreignKey = @ForeignKey(name = "fk_item_promocao") )
	private Promocao promocao;

	
	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public float getValorItem() {
		return valorItem;
	}

	public void setValorItem(float valorItem) {
		this.valorItem = valorItem;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}

}
