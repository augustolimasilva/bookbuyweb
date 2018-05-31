package util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import basica.Cliente;
import basica.Endereco;
import basica.FormaPagamento;
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

public class Main {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		try {
			Fachada f = Fachada.getInstancia();

			Date datInicial = new Date();
			datInicial.setDate(15);
			datInicial.setMonth(10);
			datInicial.setYear(115);
			Date datFinal = new Date();
			datFinal.setDate(30);
			datFinal.setMonth(10);
			datFinal.setYear(115);
			//
			// // ***********USUARIO******************************//
			Usuario u1 = new Usuario();
			u1.setLogin("atlant");
			u1.setSenha("11111");
			f.inserirUsuario(u1);
			u1 = f.buscarUsuarioLogin("atlant");

			Usuario u2 = new Usuario();
			u2.setLogin("macunaima");
			u2.setSenha("22222");
			f.inserirUsuario(u2);
			u2 = f.buscarUsuarioLogin("macunaima");

			Usuario u3 = new Usuario();
			u3.setLogin("ponteio");
			u3.setSenha("33333");
			f.inserirUsuario(u3);
			u3 = f.buscarUsuarioLogin("ponteio");
			//
			// // **********CLIENTE******************************//
			Cliente c1 = new Cliente();
			c1.setLogin("raony");
			c1.setSenha("11111");
			c1.setNome("Raony");
			c1.setEmail("raonyhudson@hotmail.com");
			c1.setTelefone("11111");
			f.inserirCliente(c1);
			c1 = f.buscarClienteLogin("raony");

			Cliente c2 = new Cliente();
			c2.setLogin("augusto");
			c2.setSenha("22222");
			c2.setNome("Augusto");
			c2.setEmail("augusto@hot.com");
			c2.setTelefone("22222");
			f.inserirCliente(c2);
			c2 = f.buscarClienteLogin("augusto");

			Cliente c3 = new Cliente();
			c3.setLogin("artur");
			c3.setSenha("33333");
			c3.setNome("Artur");
			c3.setEmail("artur@hotmail.com");
			c3.setTelefone("33333");
			f.inserirCliente(c3);
			c3 = f.buscarClienteLogin("artur");
			//
			// // **********TIPO RESTAURANTE******************************//
			TipoRestaurante tp1 = new TipoRestaurante();
			tp1.setDescricao("Churrascaria");
			f.inserirTipoRestaurante(tp1);
			tp1 = f.buscarDescricaoCompleta("Churrascaria");

			TipoRestaurante tp2 = new TipoRestaurante();
			tp2.setDescricao("Barzinho");
			f.inserirTipoRestaurante(tp2);
			tp2 = f.buscarDescricaoCompleta("Barzinho");

			TipoRestaurante tp3 = new TipoRestaurante();
			tp3.setDescricao("Temakeria");
			f.inserirTipoRestaurante(tp3);
			tp3 = f.buscarDescricaoCompleta("Temakeria");

			TipoRestaurante tp4 = new TipoRestaurante();
			tp4.setDescricao("Japonesa");
			f.inserirTipoRestaurante(tp4);
			tp4 = f.buscarDescricaoCompleta("Japonesa");

			TipoRestaurante tp5 = new TipoRestaurante();
			tp5.setDescricao("Chinesa");
			f.inserirTipoRestaurante(tp5);
			tp5 = f.buscarDescricaoCompleta("Chinesa");

			TipoRestaurante tp6 = new TipoRestaurante();
			tp6.setDescricao("Pizzaria");
			f.inserirTipoRestaurante(tp6);
			tp6 = f.buscarDescricaoCompleta("Pizzaria");

			TipoRestaurante tp7 = new TipoRestaurante();
			tp7.setDescricao("Bistro");
			f.inserirTipoRestaurante(tp7);
			tp7 = f.buscarDescricaoCompleta("Bistro");

			TipoRestaurante tp8 = new TipoRestaurante();
			tp8.setDescricao("Mexicana");
			f.inserirTipoRestaurante(tp8);
			tp8 = f.buscarDescricaoCompleta("Mexicana");

			TipoRestaurante tp9 = new TipoRestaurante();
			tp9.setDescricao("Creperia");
			f.inserirTipoRestaurante(tp9);
			tp9 = f.buscarDescricaoCompleta("Creperia");
			//
			// // **********RESTAURANTE******************************//
			Restaurante r1 = new Restaurante();
			r1.setNome("Pizzaria Atlântico");
			r1.setCnpj("84.124.605/0001-84");
			r1.setTelefone("(81)99788-6745");
			r1.setEmail("atlantico@atlantico.com.br");
			Endereco e1 = new Endereco();
			e1.setLatitude("-8.160889");
			e1.setLongitude("-34.9157731");
			e1.setRua("Avenida Ayrton Senna da Silva");
			e1.setCidade("Jab. dos Guararapes");
			e1.setBairro("Piedade");
			e1.setNumero("777");
			e1.setComplemento("Proximo ao Shop. Guararapes");
			e1.setCep("54756-515");
			r1.setUsuario(u1);
			r1.setEndereco(e1);
			List<TipoRestaurante> tipos1 = new ArrayList<TipoRestaurante>();
			tipos1.add(tp1);
			tipos1.add(tp2);
			tipos1.add(tp3);
			r1.setTipos(tipos1);
			f.inserirRestaurante(r1);
			r1 = f.buscarRestauranteNome("Pizzaria Atlântico");

			Restaurante r2 = new Restaurante();
			r2.setNome("Restaurante Macunaíma");
			r2.setCnpj("22.350.461/0001-27");
			r2.setTelefone("(81)99980-7612");
			r2.setEmail("pizgordo@pizdogordo.com.br");
			Endereco e2 = new Endereco();
			e2.setLatitude("-8.1087497");
			e2.setLongitude("-34.8931343");
			e2.setRua("Av. Engenheiro Domingos Ferreira");
			e2.setCidade("Recife");
			e2.setBairro("Boa Viagem");
			e2.setNumero("2045");
			e2.setComplemento("Próximo a Land Rover");
			e2.setCep("51011-051");
			r2.setUsuario(u2);
			r2.setEndereco(e2);
			List<TipoRestaurante> tipos2 = new ArrayList<TipoRestaurante>();
			tipos2.add(tp4);
			tipos2.add(tp5);
			tipos2.add(tp6);
			r2.setTipos(tipos2);
			f.inserirRestaurante(r2);
			r2 = f.buscarRestauranteNome("Restaurante Macunaíma");

			Restaurante r3 = new Restaurante();
			r3.setNome("Ponteio Churrascaria");
			r3.setCnpj("68.764.173/0001-10");
			r3.setTelefone("(81)98756-1234");
			r3.setEmail("nicolaspiz@nicolaspiz.com.br");
			Endereco e3 = new Endereco();
			e3.setLatitude("-8.1301145");
			e3.setLongitude("-34.9049997");
			e3.setRua("R. Visc. de Jequitinhonha");
			e3.setCidade("Recife");
			e3.setBairro("Boa Viagem");
			e3.setNumero("138");
			e3.setComplemento("Antigo Laçador");
			e3.setCep("53530-402");
			r3.setUsuario(u3);
			r3.setEndereco(e3);
			List<TipoRestaurante> tipos3 = new ArrayList<TipoRestaurante>();
			tipos3.add(tp7);
			tipos3.add(tp8);
			tipos3.add(tp9);
			r3.setTipos(tipos3);
			f.inserirRestaurante(r3);
			r3 = f.buscarRestauranteNome("Ponteio Churrascaria");

			// **********PRODUTO******************************//
			Produto p1 = new Produto();
			p1.setNome("Mussarela");
			p1.setDescricao("Pizza de queijo mussarela e orégano");
			p1.setValorProduto(10.00f);
			p1.setRestaurante(r1);
			f.inserirProduto(p1);
			p1 = f.buscarProdutoDescricao("Mussarela", r1.getIdRestaurante());

			Produto p2 = new Produto();
			p2.setNome("Calabresa");
			p2.setDescricao("Pizza de queijo mussarela, calabresa e orégano");
			p2.setValorProduto(10.00f);
			p2.setRestaurante(r1);
			f.inserirProduto(p2);
			p2 = f.buscarProdutoDescricao("Calabresa", r1.getIdRestaurante());

			Produto p3 = new Produto();
			p3.setNome("Portuguesa");
			p3.setDescricao("Pizza de queijo mussarela, tomate, cebola, pimentão, ovo, presunto e orégano");
			p3.setValorProduto(15.00f);
			p3.setRestaurante(r1);
			f.inserirProduto(p3);
			p3 = f.buscarProdutoDescricao("Portuguesa", r1.getIdRestaurante());

			Produto p4 = new Produto();
			p4.setNome("Frango c/ Catupiry");
			p4.setDescricao("Pizza de queijo mussarela, frango e catupiry");
			p4.setValorProduto(24.00f);
			p4.setRestaurante(r2);
			f.inserirProduto(p4);
			p4 = f.buscarProdutoDescricao("Frango c/ Catupiry", r2.getIdRestaurante());

			Produto p5 = new Produto();
			p5.setNome("Camarão");
			p5.setDescricao("Pizza de queijo mussarela, camarão e orégano");
			p5.setValorProduto(30.00f);
			p5.setRestaurante(r2);
			f.inserirProduto(p5);
			p5 = f.buscarProdutoDescricao("Camarão", r2.getIdRestaurante());

			Produto p6 = new Produto();
			p6.setNome("Frango");
			p6.setDescricao("Pizza de queijo mussarela, frango");
			p6.setValorProduto(20.00f);
			p6.setRestaurante(r2);
			f.inserirProduto(p6);
			p6 = f.buscarProdutoDescricao("Frango", r2.getIdRestaurante());

			Produto p7 = new Produto();
			p7.setNome("Peperonni");
			p7.setDescricao("Pizza de queijo mussarela, peperonni e orégano");
			p7.setValorProduto(13.00f);
			p7.setRestaurante(r3);
			f.inserirProduto(p7);
			p7 = f.buscarProdutoDescricao("Peperonni", r3.getIdRestaurante());

			Produto p8 = new Produto();
			p8.setNome("Atum");
			p8.setDescricao("Pizza de  queijo mussarela e atum desfiado");
			p8.setValorProduto(22.00f);
			p8.setRestaurante(r3);
			f.inserirProduto(p8);
			p8 = f.buscarProdutoDescricao("Atum", r3.getIdRestaurante());

			Produto p9 = new Produto();
			p9.setNome("Camarão c/ Catupiry");
			p9.setDescricao("Pizza de queijo mussarela, camarão e catupiry");
			p9.setValorProduto(35.00f);
			p9.setRestaurante(r3);
			f.inserirProduto(p9);
			p9 = f.buscarProdutoDescricao("Camarão c/ Catupiry", r3.getIdRestaurante());

			// **********MESA******************************//
			Mesa m1 = new Mesa();
			m1.setIdMesaRestaurante(1);
			m1.setQtdLugar(4);
			m1.setRestaurante(r1);
			m1.setStatus(Status.DISPONIVEL);
			f.inserirMesa(m1);
			m1 = f.buscarIdMesaRestaurante(1, r1.getIdRestaurante());

			Mesa m2 = new Mesa();
			m2.setIdMesaRestaurante(2);
			m2.setQtdLugar(10);
			m2.setRestaurante(r1);
			m2.setStatus(Status.DISPONIVEL);
			f.inserirMesa(m2);
			m2 = f.buscarIdMesaRestaurante(2, r1.getIdRestaurante());

			Mesa m3 = new Mesa();
			m3.setIdMesaRestaurante(3);
			m3.setQtdLugar(20);
			m3.setRestaurante(r1);
			m3.setStatus(Status.DISPONIVEL);
			f.inserirMesa(m3);
			m3 = f.buscarIdMesaRestaurante(3, r1.getIdRestaurante());

			Mesa m4 = new Mesa();
			m4.setIdMesaRestaurante(1);
			m4.setQtdLugar(4);
			m4.setRestaurante(r2);
			m4.setStatus(Status.DISPONIVEL);
			f.inserirMesa(m4);
			m4 = f.buscarIdMesaRestaurante(1, r2.getIdRestaurante());

			Mesa m5 = new Mesa();
			m5.setIdMesaRestaurante(2);
			m5.setQtdLugar(10);
			m5.setRestaurante(r2);
			m5.setStatus(Status.DISPONIVEL);
			f.inserirMesa(m5);
			m5 = f.buscarIdMesaRestaurante(2, r2.getIdRestaurante());

			Mesa m6 = new Mesa();
			m6.setIdMesaRestaurante(3);
			m6.setQtdLugar(20);
			m6.setRestaurante(r2);
			m6.setStatus(Status.DISPONIVEL);
			f.inserirMesa(m6);
			m6 = f.buscarIdMesaRestaurante(3, r2.getIdRestaurante());

			Mesa m7 = new Mesa();
			m7.setIdMesaRestaurante(1);
			m7.setQtdLugar(4);
			m7.setRestaurante(r3);
			m7.setStatus(Status.DISPONIVEL);
			f.inserirMesa(m7);
			m7 = f.buscarIdMesaRestaurante(1, r3.getIdRestaurante());

			Mesa m8 = new Mesa();
			m8.setIdMesaRestaurante(2);
			m8.setQtdLugar(10);
			m8.setRestaurante(r3);
			m8.setStatus(Status.DISPONIVEL);
			f.inserirMesa(m8);
			m8 = f.buscarIdMesaRestaurante(2, r3.getIdRestaurante());

			Mesa m9 = new Mesa();
			m9.setIdMesaRestaurante(3);
			m9.setQtdLugar(20);
			m9.setRestaurante(r3);
			m9.setStatus(Status.DISPONIVEL);
			f.inserirMesa(m9);
			m9 = f.buscarIdMesaRestaurante(3, r3.getIdRestaurante());

			// **********PROMOCAO******************************//
			Promocao pro1 = new Promocao();
			pro1.setDescricao("Desconto 1");
			pro1.setTipo(TipoPromo.NUMERICO);
			pro1.setValor(10F);
			pro1.setDataInicio(datInicial);
			pro1.setDataFinal(datFinal);
			pro1.setRestaurante(r1);
			f.inserirPromocao(pro1);
			pro1 = f.buscarPromocao(pro1);

			Promocao pro2 = new Promocao();
			pro2.setDescricao("Desconto 2");
			pro2.setTipo(TipoPromo.NUMERICO);
			pro2.setValor(20F);
			pro2.setDataInicio(datInicial);
			pro2.setDataFinal(datFinal);
			pro2.setRestaurante(r2);
			f.inserirPromocao(pro2);
			pro2 = f.buscarPromocao(pro2);

			Promocao pro3 = new Promocao();
			pro3.setDescricao("Desconto 3");
			pro3.setTipo(TipoPromo.PERCENTUAL);
			pro3.setValor(30.0F);
			pro3.setDataInicio(datInicial);
			pro3.setDataFinal(datFinal);
			pro3.setRestaurante(r3);
			f.inserirPromocao(pro3);
			pro3 = f.buscarPromocao(pro3);
			
			//******************RATE*****************************//
			Rate rate1 = new Rate();
			rate1.setCliente(c1);
			rate1.setRestaurante(r1);
			rate1.setRate(5);
			f.inserirRate(rate1);
			
			Rate rate2 = new Rate();
			rate2.setCliente(c1);
			rate2.setRestaurante(r2);
			rate2.setRate(4);
			f.inserirRate(rate2);
			
			Rate rate3 = new Rate();
			rate3.setCliente(c1);
			rate3.setRestaurante(r3);
			rate3.setRate(3);
			f.inserirRate(rate3);
			
			Rate rate4 = new Rate();
			rate4.setCliente(c2);
			rate4.setRestaurante(r1);
			rate4.setRate(2);
			f.inserirRate(rate4);
			
			Rate rate5 = new Rate();
			rate5.setCliente(c2);
			rate5.setRestaurante(r2);
			rate5.setRate(1);
			f.inserirRate(rate5);
			
			Rate rate6 = new Rate();
			rate6.setCliente(c2);
			rate6.setRestaurante(r3);
			rate6.setRate(0);
			f.inserirRate(rate6);
			
			Rate rate7 = new Rate();
			rate7.setCliente(c2);
			rate7.setRestaurante(r1);
			rate7.setRate(5);
			f.inserirRate(rate7);
			
			Rate rate8 = new Rate();
			rate8.setCliente(c2);
			rate8.setRestaurante(r2);
			rate8.setRate(4);
			f.inserirRate(rate8);
			
			Rate rate9 = new Rate();
			rate9.setCliente(c2);
			rate9.setRestaurante(r3);
			rate9.setRate(3);
			f.inserirRate(rate9);

			// // **********PEDIDO******************************//
			Pedido ped1 = new Pedido();
			ped1.setCliente(c1);
			ped1.setDataHora(new Date());
			ped1.setMesa(m1);
			ped1.setRestaurante(r1);
			ped1.setStatus(Status.ABERTO);
			f.inserirPedido(ped1);
			ped1 = f.buscarPedido(ped1);

			Pedido ped2 = new Pedido();
			ped2.setCliente(c2);
			ped2.setDataHora(new Date());
			ped2.setMesa(m2);
			ped2.setRestaurante(r2);
			ped2.setStatus(Status.ABERTO);
			f.inserirPedido(ped2);
			ped2 = f.buscarPedido(ped2);

			Pedido ped3 = new Pedido();
			ped3.setCliente(c3);
			ped3.setDataHora(new Date());
			ped3.setMesa(m3);
			ped3.setRestaurante(r3);
			ped3.setStatus(Status.ABERTO);
			f.inserirPedido(ped3);
			ped3 = f.buscarPedido(ped3);

			Pedido ped4 = new Pedido();
			ped4.setCliente(c1);
			ped4.setDataHora(new Date());
			ped4.setMesa(m4);
			ped4.setRestaurante(r1);
			ped4.setStatus(Status.ABERTO);
			f.inserirPedido(ped4);
			ped4 = f.buscarPedido(ped4);

			Pedido ped5 = new Pedido();
			ped5.setCliente(c2);
			ped5.setDataHora(new Date());
			ped5.setMesa(m5);
			ped5.setRestaurante(r2);
			ped5.setStatus(Status.ABERTO);
			f.inserirPedido(ped5);
			ped5 = f.buscarPedido(ped5);

			Pedido ped6 = new Pedido();
			ped6.setCliente(c3);
			ped6.setDataHora(new Date());
			ped6.setMesa(m6);
			ped6.setRestaurante(r3);
			ped6.setStatus(Status.ABERTO);
			f.inserirPedido(ped6);
			ped6 = f.buscarPedido(ped6);

			Pedido ped7 = new Pedido();
			ped7.setCliente(c1);
			ped7.setDataHora(new Date());
			ped7.setMesa(m7);
			ped7.setRestaurante(r1);
			ped7.setStatus(Status.ABERTO);
			f.inserirPedido(ped7);
			ped7 = f.buscarPedido(ped7);

			Pedido ped8 = new Pedido();
			ped8.setCliente(c2);
			ped8.setDataHora(new Date());
			ped8.setMesa(m8);
			ped8.setRestaurante(r2);
			ped8.setStatus(Status.ABERTO);
			f.inserirPedido(ped8);
			ped8 = f.buscarPedido(ped8);

			Pedido ped9 = new Pedido();
			ped9.setCliente(c3);
			ped9.setDataHora(new Date());
			ped9.setMesa(m9);
			ped9.setRestaurante(r3);
			f.inserirPedido(ped9);
			ped9 = f.buscarPedido(ped9);
			//
			// // ************ITEM*********************************//
			Item i1 = new Item();
			i1.setPedido(ped1);
			i1.setProduto(p1);
			i1.setPromocao(pro1);
			i1.setValorItem(p1.getValorProduto());
			i1.setQuantidade(1);
			f.inserirItem(i1);

			Item i2 = new Item();
			i2.setPedido(ped1);
			i2.setProduto(p2);
			i2.setPromocao(pro2);
			i2.setValorItem(p2.getValorProduto());
			i2.setQuantidade(1);
			f.inserirItem(i2);

			Item i3 = new Item();
			i3.setPedido(ped1);
			i3.setProduto(p3);
			i3.setPromocao(pro3);
			i3.setValorItem(p3.getValorProduto());
			i3.setQuantidade(1);
			f.inserirItem(i3);

			Item i4 = new Item();
			i4.setPedido(ped2);
			i4.setProduto(p4);
			i4.setPromocao(pro1);
			i4.setValorItem(p4.getValorProduto());
			i4.setQuantidade(1);
			f.inserirItem(i4);

			Item i5 = new Item();
			i5.setPedido(ped2);
			i5.setProduto(p5);
			i5.setPromocao(pro2);
			i5.setValorItem(p5.getValorProduto());
			i5.setQuantidade(1);
			f.inserirItem(i5);

			Item i6 = new Item();
			i6.setPedido(ped2);
			i6.setProduto(p6);
			i6.setPromocao(pro3);
			i6.setValorItem(p6.getValorProduto());
			i6.setQuantidade(1);
			f.inserirItem(i6);

			Item i7 = new Item();
			i7.setPedido(ped3);
			i7.setProduto(p7);
			i7.setPromocao(pro1);
			i7.setValorItem(p7.getValorProduto());
			i7.setQuantidade(1);
			f.inserirItem(i7);

			Item i8 = new Item();
			i8.setPedido(ped3);
			i8.setProduto(p8);
			i8.setPromocao(pro2);
			i8.setValorItem(p8.getValorProduto());
			i8.setQuantidade(1);
			f.inserirItem(i8);

			Item i9 = new Item();
			i9.setPedido(ped3);
			i9.setProduto(p9);
			i9.setPromocao(pro3);
			i9.setValorItem(p9.getValorProduto());
			i9.setQuantidade(1);
			f.inserirItem(i9);

			Item i10 = new Item();
			i10.setPedido(ped4);
			i10.setProduto(p1);
			i10.setPromocao(pro1);
			i10.setValorItem(p1.getValorProduto());
			i10.setQuantidade(1);
			f.inserirItem(i10);

			Item i11 = new Item();
			i11.setPedido(ped4);
			i11.setProduto(p2);
			i11.setPromocao(pro2);
			i11.setValorItem(p2.getValorProduto());
			i11.setQuantidade(1);
			f.inserirItem(i11);

			Item i12 = new Item();
			i12.setPedido(ped4);
			i12.setProduto(p3);
			i12.setPromocao(pro3);
			i12.setValorItem(p3.getValorProduto());
			i12.setQuantidade(1);
			f.inserirItem(i12);

			Item i13 = new Item();
			i13.setPedido(ped5);
			i13.setProduto(p4);
			i13.setPromocao(pro1);
			i13.setValorItem(p4.getValorProduto());
			i13.setQuantidade(1);
			f.inserirItem(i13);

			Item i14 = new Item();
			i14.setPedido(ped5);
			i14.setProduto(p5);
			i14.setPromocao(pro2);
			i14.setValorItem(p5.getValorProduto());
			i14.setQuantidade(1);
			f.inserirItem(i14);

			Item i15 = new Item();
			i15.setPedido(ped5);
			i15.setProduto(p6);
			i15.setPromocao(pro3);
			i15.setValorItem(p6.getValorProduto());
			i15.setQuantidade(1);
			f.inserirItem(i15);

			Item i16 = new Item();
			i16.setPedido(ped6);
			i16.setProduto(p7);
			i16.setPromocao(pro1);
			i16.setValorItem(p7.getValorProduto());
			i16.setQuantidade(1);
			f.inserirItem(i16);

			Item i17 = new Item();
			i17.setPedido(ped6);
			i17.setProduto(p8);
			i17.setPromocao(pro2);
			i17.setValorItem(p8.getValorProduto());
			i17.setQuantidade(1);
			f.inserirItem(i17);

			Item i18 = new Item();
			i18.setPedido(ped6);
			i18.setProduto(p9);
			i18.setPromocao(pro3);
			i18.setValorItem(p9.getValorProduto());
			i18.setQuantidade(1);
			f.inserirItem(i18);

			Item i19 = new Item();
			i19.setPedido(ped7);
			i19.setProduto(p1);
			i19.setPromocao(pro1);
			i19.setValorItem(p1.getValorProduto());
			i19.setQuantidade(1);
			f.inserirItem(i19);

			Item i20 = new Item();
			i20.setPedido(ped7);
			i20.setProduto(p2);
			i20.setPromocao(pro2);
			i20.setValorItem(p2.getValorProduto());
			i20.setQuantidade(1);
			f.inserirItem(i20);

			Item i21 = new Item();
			i21.setPedido(ped7);
			i21.setProduto(p3);
			i21.setPromocao(pro3);
			i21.setValorItem(p3.getValorProduto());
			i21.setQuantidade(1);
			f.inserirItem(i21);

			Item i22 = new Item();
			i22.setPedido(ped8);
			i22.setProduto(p4);
			i22.setPromocao(pro1);
			i22.setValorItem(p4.getValorProduto());
			i22.setQuantidade(1);
			f.inserirItem(i22);

			Item i23 = new Item();
			i23.setPedido(ped8);
			i23.setProduto(p5);
			i23.setPromocao(pro2);
			i23.setValorItem(p5.getValorProduto());
			i23.setQuantidade(1);
			f.inserirItem(i23);

			Item i24 = new Item();
			i24.setPedido(ped8);
			i24.setProduto(p6);
			i24.setPromocao(pro3);
			i24.setValorItem(p6.getValorProduto());
			i24.setQuantidade(1);
			f.inserirItem(i24);

			Item i25 = new Item();
			i25.setPedido(ped9);
			i25.setProduto(p7);
			i25.setPromocao(pro1);
			i25.setValorItem(p7.getValorProduto());
			i25.setQuantidade(1);
			f.inserirItem(i25);

			Item i26 = new Item();
			i26.setPedido(ped9);
			i26.setProduto(p8);
			i26.setPromocao(pro2);
			i26.setValorItem(p8.getValorProduto());
			i26.setQuantidade(1);
			f.inserirItem(i26);

			Item i27 = new Item();
			i27.setPedido(ped9);
			i27.setProduto(p9);
			i27.setPromocao(pro3);
			i27.setValorItem(p9.getValorProduto());
			i27.setQuantidade(1);
			f.inserirItem(i27);
			//
			// **********RESERVA******************************//
			Reserva res1 = new Reserva();
			res1.setCliente(c1);
			res1.setRestaurante(r1);
			res1.setData(new Date());
			f.inserirReserva(res1);

			Reserva res2 = new Reserva();
			res2.setCliente(c2);
			res2.setRestaurante(r2);
			res2.setData(new Date());
			f.inserirReserva(res2);

			Reserva res3 = new Reserva();
			res3.setCliente(c3);
			res3.setRestaurante(r3);
			res3.setData(new Date());
			f.inserirReserva(res3);

			Reserva res4 = new Reserva();
			res4.setCliente(c1);
			res4.setRestaurante(r1);
			res4.setData(new Date());
			f.inserirReserva(res4);

			Reserva res5 = new Reserva();
			res5.setCliente(c2);
			res5.setRestaurante(r2);
			res5.setData(new Date());
			f.inserirReserva(res5);

			Reserva res6 = new Reserva();
			res6.setCliente(c3);
			res6.setRestaurante(r3);
			res6.setData(new Date());
			f.inserirReserva(res6);

			Reserva res7 = new Reserva();
			res7.setCliente(c1);
			res7.setRestaurante(r1);
			res7.setData(new Date());
			f.inserirReserva(res7);

			Reserva res8 = new Reserva();
			res8.setCliente(c2);
			res8.setRestaurante(r2);
			res8.setData(new Date());
			f.inserirReserva(res8);

			Reserva res9 = new Reserva();
			res9.setCliente(c3);
			res9.setRestaurante(r3);
			res9.setData(new Date());
			f.inserirReserva(res9);

			// **********PAGAMENTO******************************//
			Pagamento pag1 = new Pagamento();
			pag1.setDataHoraPagto(new Date());
			pag1.setFormaPagamento(FormaPagamento.CREDITO);
			pag1.setValor(10.0F);
			pag1.setPedido(ped1);
			f.inserirPagamento(pag1);

			Pagamento pag2 = new Pagamento();
			pag2.setDataHoraPagto(new Date());
			pag2.setFormaPagamento(FormaPagamento.DEBITO);
			pag2.setValor(10.0F);
			pag2.setPedido(ped2);
			f.inserirPagamento(pag2);

			Pagamento pag3 = new Pagamento();
			pag3.setDataHoraPagto(new Date());
			pag3.setFormaPagamento(FormaPagamento.DINHEIRO);
			pag3.setValor(10.0F);
			pag3.setPedido(ped3);
			f.inserirPagamento(pag3);

			Pagamento pag4 = new Pagamento();
			pag4.setDataHoraPagto(new Date());
			pag4.setFormaPagamento(FormaPagamento.DEBITO);
			pag4.setValor(10.0F);
			pag4.setPedido(ped4);
			f.inserirPagamento(pag4);

			Pagamento pag5 = new Pagamento();
			pag5.setDataHoraPagto(new Date());
			pag5.setFormaPagamento(FormaPagamento.CREDITO);
			pag5.setValor(10.0F);
			pag5.setPedido(ped5);
			f.inserirPagamento(pag5);

			Pagamento pag6 = new Pagamento();
			pag6.setDataHoraPagto(new Date());
			pag6.setFormaPagamento(FormaPagamento.DINHEIRO);
			pag6.setValor(10.0F);
			pag6.setPedido(ped6);
			f.inserirPagamento(pag6);

			Pagamento pag7 = new Pagamento();
			pag7.setDataHoraPagto(new Date());
			pag7.setFormaPagamento(FormaPagamento.CREDITO);
			pag7.setValor(10.0F);
			pag7.setPedido(ped7);
			f.inserirPagamento(pag7);

			Pagamento pag8 = new Pagamento();
			pag8.setDataHoraPagto(new Date());
			pag8.setFormaPagamento(FormaPagamento.DEBITO);
			pag8.setValor(10.0F);
			pag8.setPedido(ped8);
			f.inserirPagamento(pag8);

			Pagamento pag9 = new Pagamento();
			pag9.setDataHoraPagto(new Date());
			pag9.setFormaPagamento(FormaPagamento.DINHEIRO);
			pag9.setValor(10.0F);
			pag9.setPedido(ped9);
			f.inserirPagamento(pag9);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}