package negocio;

import java.util.List;

import basica.Restaurante;
import basica.Situacao;
import basica.Usuario;
import dao.DAOFactory;
import dao.DAORestaurante;
import util.Validacao;

public class NegocioRestaurante {

	private DAORestaurante daoRestaurante;

	public NegocioRestaurante() {
		this.daoRestaurante = DAOFactory.getDAORestaurante();
	}

	public void validarCampos(Restaurante restaurante) throws Exception {
		if (restaurante.getNome().length() == 0 && restaurante.getCnpj().length() == 0
				&& restaurante.getTelefone().length() == 0 && restaurante.getEndereco().getCep().length() == 0
				&& restaurante.getEndereco().getRua().length() == 0
				&& restaurante.getEndereco().getNumero().length() == 0
				&& restaurante.getEndereco().getCidade().length() == 0
				&& restaurante.getEndereco().getBairro().length() == 0 && restaurante.getUsuario() == null
				&& restaurante.getTipos() == null) {
			throw new Exception("Por favor preencher os campos em branco.");
		}

		// Validando CNPJ
		Validacao.isValidCNPJ(restaurante.getCnpj());
		
		// Validação Nome
		if (restaurante.getNome().length() < 5) {
			throw new Exception("O campo nome deve conter no mínimo 5 caracteres.");
		}
		if (restaurante.getNome().length() > 30) {
			throw new Exception("O campo nome deve conter no máximo 30 caracteres.");
		}

		// Validação email
		if (restaurante.getEmail().length() < 5) {
			throw new Exception("O campo email deve conter no mínimo 5 caracteres.");
		}
		if (restaurante.getEmail().length() > 30) {
			throw new Exception("O campo email deve conter no máximo 30 caracteres.");
		}

		// Validando telefone
		if (restaurante.getTelefone().length() == 0) {
			throw new Exception("Por favor preencha o campo telefone.");
		}

		// Validando bairro
		if (restaurante.getEndereco().getBairro().length() < 5) {
			throw new Exception("O campo bairro deve conter no mínimo 5 caracteres.");
		}
		if (restaurante.getEndereco().getBairro().length() > 30) {
			throw new Exception("O campo bairro deve conter máximo 30 caracteres.");
		}

		// Validando cidade
		if (restaurante.getEndereco().getCidade().length() < 5) {
			throw new Exception("O campo cidade deve conter no mínimo 5 caracteres.");
		}
		if (restaurante.getEndereco().getCidade().length() > 25) {
			throw new Exception("O campo cidade deve conter máximo 30 caracteres.");
		}

		// Validando Latitude
		// if (restaurante.getEndereco().getLatitude() == 0) {
		// throw new Exception("Por favor preencha uma latitude diferente de
		// 0.");
		// }

		// Validando Longitude
		// if (restaurante.getEndereco().getLongitude() == 0) {
		// throw new Exception("Por favor preencha uma longitude diferente de
		// 0");
		// }

		// Validando numero
		if (restaurante.getEndereco().getNumero().length() == 0) {
			throw new Exception("Por favor preencher o campo numero.");
		}

		// Validando tipo
		if (restaurante.getTipos() == null || restaurante.getTipos().size() < 1) {
			throw new Exception("Por favor selecione pelo menos um tipo.");
		}
	}

	public void inserirRestaurante(Restaurante restaurante) throws Exception {
		// Validando todos campos
		this.validarCampos(restaurante);
		Restaurante restauranteRetorno = this.daoRestaurante.buscarRestauranteCnpj(restaurante.getCnpj());

		if (restauranteRetorno != null) {
			throw new Exception("Esse CNPJ já se encontra cadastrado, por favor informe outro CNPJ.");
		} else {
			restaurante.setSituacao(Situacao.ATIVO);
			this.daoRestaurante.inserir(restaurante);
		}
	}

	public Restaurante buscarRestaurante(Restaurante restaurante) throws Exception {
		if (this.daoRestaurante.buscarId(restaurante.getIdRestaurante()) == null) {
			throw new Exception("Restaurante não cadastrado.");
		} else {
			return this.daoRestaurante.buscarId(restaurante.getIdRestaurante());
		}
	}

	public Restaurante buscarRestaurante(int id) throws Exception {
		if (id <= 0) {
			throw new Exception("Por favor informar um ID maior que 0.");
		}
		if (this.daoRestaurante.buscarId(id) == null) {
			throw new Exception("Restaurante não cadastrado.");
		} else {
			return this.daoRestaurante.buscarId(id);
		}
	}

	public Restaurante buscarRestauranteNome(String nome) throws Exception {
		if (nome.isEmpty()) {
			throw new Exception("informe um nome.");
		}
		if (this.daoRestaurante.buscarRestauranteNome(nome) == null) {
			throw new Exception("Restaurante não cadastrado.");
		} else {
			return this.daoRestaurante.buscarRestauranteNome(nome);
		}
	}

	public List<Restaurante> buscarTudo() {
		return this.daoRestaurante.buscarTudo();
	}

	public void removerRestaurante(Restaurante restaurante) throws Exception {
		Restaurante restauranteRetorno = this.daoRestaurante.buscarRestauranteCnpj(restaurante.getCnpj());
		if (restauranteRetorno != null) {
			restaurante.setSituacao(Situacao.INATIVO);
			this.daoRestaurante.alterar(restaurante);
		} else {
			throw new Exception("Restaurante não cadastrado!");
		}
	}

	public void alterarRestaurante(Restaurante restaurante) throws Exception {
		Restaurante restauranteRetorno = this.daoRestaurante.buscarRestauranteCnpj(restaurante.getCnpj());
		if (restauranteRetorno == null) {
			throw new Exception("Restaurante não cadastrado");
		}

		this.validarCampos(restaurante);
		this.daoRestaurante.alterar(restaurante);
	}

	public void removerRestauranteCnpj(String cnpj) throws Exception {

		if (cnpj.length() == 0) {
			throw new Exception("Por favor preencha o campo CNPJ.");
		}

		Restaurante restauranteRetorno = this.daoRestaurante.buscarRestauranteCnpj(cnpj);

		if (restauranteRetorno == null) {
			throw new Exception("Restaurante não cadastrado, informe um CNPJ válido.");
		} else {
			restauranteRetorno.setSituacao(Situacao.INATIVO);
			this.daoRestaurante.alterar(restauranteRetorno);
		}
	}

	public Restaurante buscarRestauranteCnpj(String cnpj) throws Exception {
		/*
		 * if(cnpj.length() < 14){ throw new Exception(
		 * "Preencha corretamente o campo CNPJ"); }
		 * 
		 * if(cnpj.length() > 14){ throw new Exception(
		 * "O campo CNPJ não pode ter mais de '14' caracteres"); }
		 */

		if (cnpj.length() == 0) {
			throw new Exception("Por favor preencha o campo CNPJ");
		}

		if (this.daoRestaurante.buscarRestauranteCnpj(cnpj) == null) {
			throw new Exception("Restaurante não cadastrado, informe um CNPJ válido.");
		} else {
			return this.daoRestaurante.buscarRestauranteCnpj(cnpj);
		}
	}

	public void alterarRestauranteCnpj(String cnpj) throws Exception {

		Restaurante restauranteRetorno = this.daoRestaurante.buscarRestauranteCnpj(cnpj);
		if (restauranteRetorno == null) {
			throw new Exception("Restaurante não cadastrado, informe um CNPJ válido.");
		} else {
			this.daoRestaurante.alterar(restauranteRetorno);
		}
	}

	public List<Restaurante> listarRestaurantesBairro(String bairro) throws Exception {

		if (bairro.length() < 5) {
			throw new Exception("Por favor preencha o campo bairro corretamente.");
		}

		if (bairro.length() > 10) {
			throw new Exception("Por favor preencha o campo bairro corretamente.");
		}

		return this.daoRestaurante.listarRestaurantesBairro(bairro);
	}

	public List<Restaurante> listarRestaurantesCidade(String cidade) throws Exception {

		if (cidade.length() < 5) {
			throw new Exception("Por favor preencha o campo cidade corretamente.");
		}

		if (cidade.length() > 25) {
			throw new Exception("Por favor preencha o campo cidade corretamente.");
		}

		return this.daoRestaurante.listarRestaurantesCidade(cidade);
	}

	public List<Restaurante> listarRestaurantesNome(String nome) throws Exception {

		if (nome.length() < 5) {
			throw new Exception("Por favor preencha o campo nome corretamente.");
		}

		if (nome.length() > 15) {
			throw new Exception("Por favor preencha o campo nome corretamente.");
		}

		return this.daoRestaurante.listarRestaurantesNome(nome);
	}

	public List<Restaurante> listarRestaurantesUsuario(Usuario u) throws Exception {

		if (u == null) {
			throw new Exception("Por favor informe o usuario.");
		}

		return this.daoRestaurante.listarRestaurantesUsuario(u);
	}
}
