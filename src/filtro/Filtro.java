package filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter("*.xhtml")
public class Filtro implements Filter {

	public Filtro() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		if (session.getAttribute("usuarioLoagado") != null || req.getRequestURI().endsWith("Login.xhtml")) {
			chain.doFilter(request, response);

		} else {
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect("Login.xhtml");
			return;
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
}
