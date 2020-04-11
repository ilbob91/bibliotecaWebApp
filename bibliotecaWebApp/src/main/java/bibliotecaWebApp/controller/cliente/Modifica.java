package bibliotecaWebApp.controller.cliente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import bibliotecaWebApp.model.Utente;
import bibliotecaWebApp.repository.GestioneDb;

@WebServlet(name = "modifica", urlPatterns = { "/cliente/modifica" })
@MultipartConfig
public class Modifica extends HttpServlet{
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	GestioneDb db;
	HttpSession session = req.getSession();
	String username = (String) session.getAttribute("username");
	try {
		db = new GestioneDb();
		Part p = req.getPart("image");
		Utente u = db.modificaImmagine(p.getInputStream() , username);
		db.creaImmagine(req.getParameter("username"));
		//req.setAttribute("username", req.getParameter("username"));
		req.setAttribute("utente", u);
		req.setAttribute("mess", "immagine modificata");
		db.close();
		req.getRequestDispatcher("/opzioniCliente.jsp").forward(req, resp);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	}
	
	
	
}
