package negocio;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import basica.Produto;
import basica.Restaurante;
import basica.Situacao;
import dao.DAOFactory;
import dao.DAOProduto;

public class NegocioProduto {

	private DAOProduto daoProduto;

	public NegocioProduto() {
		this.daoProduto = DAOFactory.getDAOProduto();
	}

	public boolean verificarCampos(Produto produto) throws Exception {
		if (produto.getNome().trim().isEmpty()) {
			throw new Exception("Preencha o campo nome.");

		} else if (produto.getRestaurante() == null || produto.getRestaurante().getIdRestaurante() == 0) {
			throw new Exception("Informe o restaurante.");

		} else if (produto.getValorProduto() <= 0) {
			throw new Exception("Informe um valor maior que 0.");
		}else if (produto.getDescricao().trim().isEmpty()) {
			throw new Exception("Preencha o campo descrição.");

		}

		return true;
	}

	public void inserirProduto(Produto produto) throws Exception {
		if (verificarCampos(produto)) {
			List<Produto> produtos = daoProduto.buscarProdutoRestaurante(produto.getRestaurante());
			for (int i = 0; i < produtos.size(); i++) {
				if (produtos.get(i).getNome().trim().equals(produto.getNome().trim())) {
					throw new Exception("Produto já cadastrado para esse restaurante.");
				}
			}
			produto.setSituacao(Situacao.ATIVO);
			this.daoProduto.inserir(produto);
		}

	}

	public void removerProduto(Produto produto) throws Exception {
		buscarProduto(produto);
		produto.setSituacao(Situacao.INATIVO);
		this.daoProduto.alterar(produto);
	}

	public void atualizarProduto(Produto produto) throws Exception {
		buscarProduto(produto);
		if (verificarCampos(produto)) {

			List<Produto> produtos = daoProduto.buscarProdutoRestaurante(produto.getRestaurante());
			for (int i = 0; i < produtos.size(); i++) {
				if (produtos.get(i).getNome().trim().equals(produto.getNome().trim())
						&& (produto.getValorProduto() != produtos.get(i).getValorProduto())) {
					this.daoProduto.alterar(produto);
					return;
				} else if (produtos.get(i).getNome().trim().equals(produto.getNome().trim())) {
					throw new Exception("Produto já cadastrado para esse restaurante.");
				}
			}
			this.daoProduto.alterar(produto);
		}
	}

	public Produto buscarProduto(Produto produto) throws Exception {

		if (this.daoProduto.buscarId(produto.getIdProduto()) == null) {
			throw new Exception("Produto não cadastrado.");
		} else {
			return this.daoProduto.buscarId(produto.getIdProduto());
		}

	}

	public Produto buscarProduto(int id) throws Exception {

		if (this.daoProduto.buscarId(id) == null) {
			throw new Exception("Produto não cadastrado.");
		} else {
			return this.daoProduto.buscarId(id);
		}

	}

	public Produto buscarProdutoDescricao(String descricao, int idRestaurante) throws Exception {
		if (idRestaurante <= 0) {
			throw new Exception("Informe um restaurante.");

		}

		if (descricao.trim().isEmpty()) {
			throw new Exception("Informe uma descricao.");
		}
		return this.daoProduto.buscarProdutoDescricao(descricao, idRestaurante);

	}

	public List<Produto> buscarTudo() {
		return this.daoProduto.buscarTudo();
	}

	public List<Produto> buscarProdutoRestaurante(Restaurante r) throws Exception {
		if (r == null || r.getIdRestaurante() == 0) {
			throw new Exception("Informe um restaurante.");
		} else {
			return this.daoProduto.buscarProdutoRestaurante(r);
		}

	}
}
