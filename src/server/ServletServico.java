package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servico")
public class ServletServico extends HttpServlet {

	private static final long serialVersionUID = -6773508913368919929L;

	private String ip;

	// http://ip:porta/bookbuy/servico?requisicao=LOGAR&LOGIN=admin&SENHA=admin
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter printer = resp.getWriter();

		this.ip = req.getRemoteAddr();

		System.out.println("Requisição do ip " + this.ip);
		System.out.println(new Date());

		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, HEAD");

		String requisicao = req.getParameter("requisicao");

		if (requisicao.equals("LOGAR")) {
			// Service faz algo
			String login = req.getParameter("LOGIN");
			String senha = req.getParameter("SENHA");

			//
			resp.getWriter().write("OK");
		}

	}
}
