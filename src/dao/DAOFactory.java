package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAOFactory {

	private static EntityManager manager;
	private static final EntityManagerFactory factory;

	static {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		factory = Persistence.createEntityManagerFactory("bookbuy");
		if (manager == null || !manager.isOpen()) {
			manager = factory.createEntityManager();
		}
	}

	public static DAOCliente getDAOCliente() {
		DAOCliente dao = new DAOCliente(manager);
		return dao;
	}

	public static DAOUsuario getDAOUsuario() {
		DAOUsuario dao = new DAOUsuario(manager);
		return dao;
	}

	public static DAOMesa getDAOMesa() {
		DAOMesa dao = new DAOMesa(manager);
		return dao;
	}

	public static DAOItem getDAOItem() {
		DAOItem dao = new DAOItem(manager);
		return dao;
	}

	public static DAOPagamento getDAOPagamento() {
		DAOPagamento dao = new DAOPagamento(manager);
		return dao;
	}

	public static DAOPedido getDAOPedido() {
		DAOPedido dao = new DAOPedido(manager);
		return dao;
	}

	public static DAOProduto getDAOProduto() {
		DAOProduto dao = new DAOProduto(manager);
		return dao;
	}

	public static DAOPromocao getDAOPromocao() {
		DAOPromocao dao = new DAOPromocao(manager);
		return dao;
	}

	public static DAOReserva getDAOReserva() {
		DAOReserva dao = new DAOReserva(manager);
		return dao;
	}

	public static DAORestaurante getDAORestaurante() {
		DAORestaurante dao = new DAORestaurante(manager);
		return dao;
	}

	public static DAOTipoRestaurante getDAOTipoRestaurante() {
		DAOTipoRestaurante dao = new DAOTipoRestaurante(manager);
		return dao;
	}

	public static DAORate getDAORate() {
		DAORate dao = new DAORate(manager);
		return dao;
	}
}