package bibliotecaWebApp.controller.cliente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "tornaIndietro", urlPatterns = { "/cliente/tornaIndietro" , "/tornaindietro"})
public class TornaIndietro extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		HttpSession session = req.getSession();
		session.getAttribute("username");
		
		resp.sendRedirect(req.getContextPath() + "/opzioniCliente.jsp");
		
	}
	
}
