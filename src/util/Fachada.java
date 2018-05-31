package util;

import java.util.Date;
import java.util.List;

import basica.Cliente;
import basica.Item;
import basica.Mesa;
import basica.Pagamento;
import basica.Pedido;
import basica.Produto;
import basica.Promocao;
import basica.Rate;
import basica.Reserva;
import basica.Restaurante;
import basica.Status;
import basica.TipoPromo;
import basica.TipoRestaurante;
import basica.Usuario;
import negocio.NegocioCliente;
import negocio.NegocioItem;
import negocio.NegocioMesa;
import negocio.NegocioPagamento;
import negocio.NegocioPedido;
import negocio.NegocioProduto;
import negocio.NegocioPromocao;
import negocio.NegocioRate;
import negocio.NegocioReserva;
import negocio.NegocioRestaurante;
import negocio.NegocioTipoRestaurante;
import negocio.NegocioUsuario;

public class Fachada {

	private static Fachada instancia;
	private NegocioRestaurante restaurante;
	private NegocioCliente cliente;
	private NegocioProduto produto;
	private NegocioMesa mesa;
	private NegocioItem item;
	private NegocioPagamento pagamento;
	private NegocioPedido pedido;
	private NegocioPromocao promocao;
	private NegocioReserva reserva;
	private NegocioUsuario usuario;
	private NegocioTipoRestaurante tipoRestaurante;
	private NegocioRate rate;

	private Fachada() {
		this.restaurante = new NegocioRestaurante();
		this.cliente = new NegocioCliente();
		this.produto = new NegocioProduto();
		this.mesa = new NegocioMesa();
		this.item = new NegocioItem();
		this.pagamento = new NegocioPagamento();
		this.pedido = new NegocioPedido();
		this.promocao = new NegocioPromocao();
		this.reserva = new NegocioReserva();
		this.usuario = new NegocioUsuario();
		this.tipoRestaurante = new NegocioTipoRestaurante();
		this.rate = new NegocioRate();
	}

	public static Fachada getInstancia() {
		if (instancia == null) {
			instancia = new Fachada();
		}
		return instancia;
	}

	/********** RESTAURANTE **********/

	public void inserirRestaurante(Restaurante res) throws Exception {
		this.restaurante.inserirRestaurante(res);
	}

	public Restaurante buscarRestaurante(Restaurante res) throws Exception {
		return this.restaurante.buscarRestaurante(res);
	}

	public Restaurante buscarRestaurante(int id) throws Exception {
		return this.restaurante.buscarRestaurante(id);
	}

	public Restaurante buscarRestauranteNome(String nome) throws Exception {
		return this.restaurante.buscarRestauranteNome(nome);
	}

	public List<Restaurante> buscarTodosRestaurante() {
		return this.restaurante.buscarTudo();
	}

	public void removerRestaurante(Restaurante res) throws Exception {
		this.restaurante.removerRestaurante(res);
	}

	public void alterarRestaurante(Restaurante res) throws Exception {
		this.restaurante.alterarRestaurante(res);
	}

	public void removerRestauranteCnpj(String cnpj) throws Exception {
		this.restaurante.removerRestauranteCnpj(cnpj);
	}

	public Restaurante buscarRestauranteCnpj(String cnpj) throws Exception {
		return this.restaurante.buscarRestauranteCnpj(cnpj);
	}

	public void alterarRestauranteCnpj(Restaurante res) {
		this.alterarRestauranteCnpj(res);
	}

	public List<Restaurante> listarRestaurantesBairro(String bairro) throws Exception {
		return this.restaurante.listarRestaurantesBairro(bairro);
	}

	public List<Restaurante> listarRestaurantesCidade(String cidade) throws Exception {
		return this.restaurante.listarRestaurantesCidade(cidade);
	}

	public List<Restaurante> listarRestaurantesNome(String nome) throws Exception {
		return this.restaurante.listarRestaurantesNome(nome);
	}

	public List<Restaurante> listarRestaurantesUsuario(Usuario u) throws Exception {
		return this.restaurante.listarRestaurantesUsuario(u);
	}

	/********** PRODUTO **********/

	public void inserirProduto(Produto produto) throws Exception {
		this.produto.inserirProduto(produto);
	}

	public void removerProduto(Produto produto) throws Exception {
		this.produto.removerProduto(produto);
	}

	public void alterarProduto(Produto produto) throws Exception {
		this.produto.atualizarProduto(produto);
	}

	public Produto buscarProduto(Produto produto) throws Exception {
		return this.produto.buscarProduto(produto);
	}

	public Produto buscarProduto(int i) throws Exception {
		return this.produto.buscarProduto(i);
	}

	public List<Produto> buscarTodosProduto() {
		return this.produto.buscarTudo();
	}

	public List<Produto> buscarProdutoRestaurante(Restaurante r) throws Exception {
		return this.produto.buscarProdutoRestaurante(r);
	}

	public Produto buscarProdutoDescricao(String descricao, int idRestaurante) throws Exception {
		return this.produto.buscarProdutoDescricao(descricao, idRestaurante);
	}

	/********** ITEM **********/

	public void inserirItem(Item item) throws Exception {
		this.item.inserirItem(item);
	}

	public void removerItem(Item item) throws Exception {
		this.item.removerItem(item);
	}

	public void alterarItem(Item item) throws Exception {
		this.item.alterarItem(item);
	}

	public Item buscarItem(Item item) throws Exception {
		return this.item.buscarItem(item);
	}

	public List<Item> buscarTodosItem() {
		return this.item.buscarTudo();
	}

	public List<Item> buscarItensPedido(int idPedido) throws Exception {
		return this.item.buscarItensPedido(idPedido);
	}

	/********** PAGAMENTO **********/

	public void inserirPagamento(Pagamento pagamento) throws Exception {
		this.pagamento.inserirPagamento(pagamento);
	}

	public void removerPagamento(Pagamento pagamento) throws Exception {
		this.pagamento.removerPagamento(pagamento);
	}

	public void alterarPagamento(Pagamento pagamento) throws Exception {
		this.pagamento.alterarPagamento(pagamento);
	}

	public Pagamento buscarPagamento(Pagamento pagamento) throws Exception {
		return this.pagamento.buscarPagamento(pagamento);
	}

	public Pagamento buscarPagamento(int id) throws Exception {
		return this.pagamento.buscarPagamento(id);
	}

	public List<Pagamento> buscarTodosPagamento() {
		return this.pagamento.buscarTodosPagamento();
	}

	public Pagamento buscarPagamentoPedido(Pedido ped) throws Exception {
		return this.pagamento.buscarPagamentoPedido(ped);
	}

	/********** PEDIDO **********/

	public void inserirPedido(Pedido pedido) throws Exception {
		this.pedido.inserirPedido(pedido);
	}

	public void removerPedido(Pedido pedido) throws Exception {
		this.pedido.removerPedido(pedido);
	}

	public void alterarPedido(Pedido pedido) throws Exception {
		this.pedido.alterarPedido(pedido);
	}

	public Pedido buscarPedido(Pedido pedido) throws Exception {
		return this.pedido.buscarPedido(pedido);
	}

	public Pedido buscarPedido(int id) throws Exception {
		return this.pedido.buscarPedido(id);
	}

	public List<Pedido> buscarTodosPedido() {
		return this.pedido.buscarTudo();
	}

	public List<Pedido> buscarPedidosCliente(Cliente cliente) {
		return this.pedido.buscarPedidosCliente(cliente);
	}

	public List<Pedido> buscarPedidosRestaurante(Restaurante restaurante) {
		return this.pedido.buscarPedidosRestaurante(restaurante);
	}

	public List<Pedido> buscarPedidosData(Date data, int idRestaurante) throws Exception {
		return this.pedido.buscarPedidosData(data, idRestaurante);
	}

	/********** PROMOÇÃO **********/

	public void inserirPromocao(Promocao promocao) throws Exception {
		this.promocao.inserirPromocao(promocao);
	}

	public Promocao buscarPromocao(Promocao promocao) throws Exception {
		return this.promocao.buscarPromocao(promocao);
	}

	public Promocao buscarPromocao(int idPromocao, int idRestaurante) throws Exception {
		return this.promocao.buscarPromocao(idPromocao, idRestaurante);
	}

	public List<Promocao> buscarTodasPromocao() {
		return this.promocao.buscarTudo();
	}

	public List<Promocao> buscarDataPromocao(Date dataInicio, Date dataFinal, int idRestaurante) throws Exception {
		return this.promocao.buscarDataPromocao(dataInicio, dataFinal, idRestaurante);
	}

	public List<Promocao> buscarTipoPromocao(TipoPromo tipo, int idRestaurante) throws Exception {
		return this.promocao.buscarTipoPromocao(tipo, idRestaurante);
	}

	public void removerPromocao(Promocao promocao) throws Exception {
		this.promocao.removerPromocao(promocao);
	}

	public void alterarPromocao(Promocao promocao) throws Exception {
		this.promocao.alterarPromocao(promocao);
	}

	public List<Promocao> buscarPromoRestaurante(int idRestaurante) throws Exception {
		return this.promocao.buscarPromoRestaurante(idRestaurante);
	}

	// public List<Promocao> buscarDescricaoParcilPromocao(String descricao, int
	// idRestaurante) throws Exception {
	// return this.promocao.buscarDescricaoParcilPromocao(descricao,
	// idRestaurante);
	// }

	// public List<Item> buscarItensPromocao(int idPromocao, int idRestaurante)
	// throws Exception {
	// return this.promocao.buscarItensPromocao(idPromocao, idRestaurante);
	// }

	// public void removerPromocao(int idPromocao, int idRestaurante) throws
	// Exception {
	// this.promocao.removerPromocao(idPromocao, idRestaurante);
	// }

	/********** RESERVA **********/

	public void inserirReserva(Reserva reserva) throws Exception {
		this.reserva.inserirReserva(reserva);
	}

	public void removerReserva(Reserva reserva) throws Exception {
		this.reserva.removerReserva(reserva);
	}

	public void alterarReserva(Reserva reserva) throws Exception {
		this.reserva.alterarReserva(reserva);
	}

	public Reserva buscarReserva(Reserva reserva) throws Exception {
		return this.reserva.buscarReserva(reserva);
	}

	public Reserva buscarReserva(int idReserva) throws Exception {
		return this.reserva.buscarReserva(idReserva);
	}

	public List<Reserva> buscarReservaCliente(int idCliente) throws Exception {
		return this.reserva.buscarReservaCliente(idCliente);
	}

	public List<Reserva> buscarReservaRestaurante(int idRestaurante) throws Exception {
		return this.reserva.buscarReservaRestaurante(idRestaurante);
	}

	public List<Reserva> buscarReservaDisponivel(int idRestaurante) throws Exception {
		return this.reserva.buscarReservaDisponivel(idRestaurante);
	}

	public List<Reserva> buscarReservaDataRestaurante(Date dataHora, int idRestaurante) throws Exception {
		return this.reserva.buscarReservaDataRestaurante(dataHora, idRestaurante);
	}

	public List<Reserva> buscarTodosReserva() {
		return this.reserva.buscarTudo();
	}

	/********** CLIENTE **********/

	public void inserirCliente(Cliente cli) throws Exception {
		this.cliente.inserirCliente(cli);
	}

	public void removerCliente(Cliente cli) throws Exception {
		this.cliente.removerCliente(cli);
	}

	public void alterarCliente(Cliente cli) throws Exception {
		this.cliente.alterarCliente(cli);
	}

	public void validarLoginCliente(String login, String senha) throws Exception {
		this.cliente.validarLogin(login, senha);
	}

	public Cliente buscarClienteEmail(String email) throws Exception {
		return this.cliente.buscarClienteEmail(email);
	}

	public Cliente buscarClienteLogin(String login) throws Exception {
		return this.cliente.buscarClienteLogin(login);
	}
	
	public List<Cliente> buscarClienteLoginList(String login){
		return this.cliente.buscarClienteLoginList(login);	
	}

	public Cliente buscarCliente(Cliente cli) throws Exception {
		return this.cliente.buscarCliente(cli);
	}

	public List<Cliente> buscarTodosClientes() {
		return this.cliente.buscarTudo();
	}

	// public Cliente buscarCliente(int id) throws Exception {
	// return this.cliente.buscarCliente(id);
	// }

	// public void removerClienteEmail(String email) throws Exception {
	// this.cliente.removerClienteEmail(email);
	// }

	// public List<Cliente> listarClientes(String nome) throws Exception {
	// return this.cliente.listarClientes(nome);
	// }

	// public Cliente buscarClienteTelefone(String telefone) throws Exception {
	// return this.cliente.buscarClienteTelefone(telefone);
	// }

	// public void alterarClienteEmail(String email) throws Exception {
	// this.cliente.alterarClienteEmail(email);
	// }

	/*********** USUARIO **********/

	public void inserirUsuario(Usuario u) throws Exception {
		this.usuario.inserirUsuario(u);
	}

	public void alterarUsuario(Usuario u) throws Exception {
		this.usuario.alterarUsuario(u);
	}

	public void removerUsuario(Usuario u) throws Exception {
		this.usuario.removerUsuario(u);
	}

	public boolean validarLoginUsuario(String login, String senha) throws Exception {
		return this.usuario.validarLogin(login, senha);
	}

	public Usuario buscarUsuarioLogin(String login) {
		return this.usuario.buscarUsuarioLogin(login);
	}

	public Usuario buscarUsuario(Usuario u) throws Exception {
		return this.usuario.buscarUsuario(u);
	}

	/********** MESA **********/

	public void inserirMesa(Mesa mesa) throws Exception {
		this.mesa.inserirMesa(mesa);
	}

	public void removerMesa(Mesa mesa) throws Exception {
		this.mesa.removerMesa(mesa);
	}

	public void alterarMesa(Mesa mesa) throws Exception {
		this.mesa.alterarMesa(mesa);
	}

	public List<Mesa> buscarTodosMesa() throws Exception {
		return this.mesa.buscarTudo();
	}

	public List<Mesa> buscarMesaRestaurante(Restaurante r) throws Exception {
		return this.buscarMesaRestaurante(r);
	}

	public Mesa buscarIdMesaRestaurante(int idMesaRestaurante, int idRestaurante) throws Exception {
		return this.mesa.buscarIdMesaRestaurante(idMesaRestaurante, idRestaurante);
	}

	public List<Mesa> buscarMesaRestaurante(int idRestaurante) throws Exception {
		return this.mesa.buscarMesaRestaurante(idRestaurante);
	}

	public List<Mesa> buscarMesaStatus(Status status, int idMesa) throws Exception {
		return this.mesa.buscarMesaStatus(status, idMesa);
	}

	public List<Mesa> buscarQtdLugar(int qtdLugar, int idRestaurante) throws Exception {
		return this.mesa.buscarQtdLugar(qtdLugar, idRestaurante);
	}

	public List<Mesa> buscarMesasDisponiveis(int idRestaurante) throws Exception {
		return this.mesa.buscarMesasDisponiveis(idRestaurante);
	}

	// public Mesa buscarMesa(int idMesa) throws Exception {
	// return this.mesa.buscarMesa(idMesa);
	// }

	// public Mesa buscarMesa(Mesa mesa) throws Exception {
	// return this.mesa.buscarMesa(mesa.getIdMesa());
	// }

	// public void removerMesa(int idMesa, int idRestaurante) throws Exception {
	// mesa.removerMesa(idMesa, idRestaurante);
	// }

	// public void removerIdMesaRestaurante(Mesa mesa) throws Exception {
	// this.mesa.removerIdMesaRestaurante(mesa);
	// }

	/********** TIPO RESTAURANTE **********/

	public void inserirTipoRestaurante(TipoRestaurante tipoRestaurante) throws Exception {
		this.tipoRestaurante.inserirTipoRestaurante(tipoRestaurante);
	}

	public void removerTipoRestaurante(TipoRestaurante tipoRestaurante) throws Exception {
		this.tipoRestaurante.removerTipoRestaurante(tipoRestaurante);
	}

	public void alterarTipoRestaurante(TipoRestaurante tipoRestaurante) throws Exception {
		this.tipoRestaurante.alterarTipoRestaurante(tipoRestaurante);
	}

	public TipoRestaurante buscarTipoRestaurante(TipoRestaurante tipoRestaurante) throws Exception {
		return this.tipoRestaurante.buscarTipoRestaurante(tipoRestaurante);
	}

	public TipoRestaurante buscarTipoRestaurante(int id) throws Exception {
		return this.tipoRestaurante.buscarTipoRestaurante(id);
	}

	public List<TipoRestaurante> buscarTodosTipoRestaurante() {
		return this.tipoRestaurante.buscarTodosTipoRestaurante();
	}

	public TipoRestaurante buscarDescricaoCompleta(String descricao) throws Exception {
		return this.tipoRestaurante.buscarDescricaoCompleta(descricao);
	}

	/********************* RATE ****************/

	public void inserirRate(Rate rate) throws Exception {
		this.rate.inserirRate(rate);
	}

	public void removerRate(Rate rate) throws Exception {
		this.rate.alterarRate(rate);
	}

	public void alterarRate(Rate rate) throws Exception {
		this.rate.alterarRate(rate);
	}

	public List<Rate> buscarTodosRate() {
		return this.rate.buscarTodosRate();
	}

	public Rate buscarPorRate(int rate) {
		return this.rate.buscarPorRate(rate);
	}

	public Rate buscarIdRate(int id) {
		return this.rate.buscarIdRate(id);
	}

	public List<Rate> buscarRateRestaurante(Restaurante r) {
		return this.rate.buscarRateRestaurante(r);
	}

	public List<Rate> buscarRateCliente(Cliente c) {
		return this.rate.buscarRateCliente(c);
	}

	public List<Rate> buscarRateRestauranteCliente(Restaurante r, Cliente c) {
		return this.rate.buscarRateRestauranteCliente(r, c);
	}
	
	public int calcularRate(List<Rate> rates) {
		return this.rate.calcularRate(rates);
	}
}