package bibliotecaWebApp.controller.cliente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "tornaIndietro", urlPatterns = { "/tornaIndietro" })
public class TornaIndietro extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		req.setAttribute("username", req.getParameter("username"));
		req.getRequestDispatcher("opzioniCliente.jsp").forward(req, resp);
		
	}
	
	
}
