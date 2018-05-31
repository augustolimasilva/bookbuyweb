package negocio;

import java.util.List;

import basica.Item;
import basica.Promocao;
import basica.Situacao;
import basica.TipoPromo;
import dao.DAOFactory;
import dao.DAOItem;

public class NegocioItem {

	private DAOItem daoItem;

	public NegocioItem() {
		this.daoItem = DAOFactory.getDAOItem();
	}

	public void inserirItem(Item item) throws Exception {
		this.daoItem.inserir(item);
	}

	public Item buscarItem(Item item) throws Exception {

		if (this.daoItem.buscarId(item.getIdItem()) == null) {
			throw new Exception("Item não cadastrado.");
		} else {
			return this.daoItem.buscarId(item.getIdItem());
		}

	}
	
	public List<Item> buscarItensPedido(int idPedido) throws Exception {
		if (idPedido == 0) {
			throw new Exception("Favor selecionar um pedido!");
		}
		if (this.daoItem.buscarItensPedido(idPedido) != null) {
			return this.daoItem.buscarItensPedido(idPedido);
		} else {
			throw new Exception("Promoção não encontrada!");
		}
	}

	public List<Item> buscarTudo() {
		return this.daoItem.buscarTudo();

	}

	public void removerItem(Item item) throws Exception {
		this.daoItem.alterar(item);
	}

	public void alterarItem(Item item) throws Exception {
		this.daoItem.alterar(item);
	}

}
