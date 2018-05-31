package negocio;

import java.util.List;

import basica.Cliente;
import basica.Rate;
import basica.Restaurante;
import dao.DAOFactory;
import dao.DAORate;

public class NegocioRate {

	private DAORate daoRate;

	public NegocioRate() {
		this.daoRate = DAOFactory.getDAORate();
	}

	public void inserirRate(Rate rate) throws Exception {
		this.daoRate.inserir(rate);
	}

	public void removerRate(Rate rate) throws Exception {
		this.daoRate.alterar(rate);
	}

	public void alterarRate(Rate rate) throws Exception {
		this.daoRate.alterar(rate);
	}

	public List<Rate> buscarTodosRate() {
		return this.daoRate.buscarTodosRate();
	}

	public Rate buscarPorRate(int rate) {
		return this.buscarPorRate(rate);
	}

	public Rate buscarIdRate(int id) {
		return this.daoRate.buscarId(id);
	}

	public List<Rate> buscarRateRestaurante(Restaurante r) {
		return this.daoRate.buscarRateRestaurante(r);
	}

	public List<Rate> buscarRateCliente(Cliente c) {
		return this.daoRate.buscarRateCliente(c);
	}

	public List<Rate> buscarRateRestauranteCliente(Restaurante r, Cliente c) {
		return this.daoRate.buscarRateRestauranteCliente(r, c);
	}

	public int calcularRate(List<Rate> rates) {
		float f = 0.0f;
		for (Rate rate : rates) {
			f += rate.getRate();
		}
		return Math.round(f / rates.size());
	}
}
