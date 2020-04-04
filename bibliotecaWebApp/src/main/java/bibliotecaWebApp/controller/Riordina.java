package bibliotecaWebApp.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bibliotecaWebApp.model.Libro;
import bibliotecaWebApp.repository.GestioneDb;

@WebServlet(name = "riordina", urlPatterns = "/riordina")
public class Riordina extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String titolo = req.getParameter("titolo");
		int quantita = Integer.parseInt(req.getParameter("quantita"));
		try {
			GestioneDb gest = new GestioneDb();
			int quantitaVecchia = gest.checkQuantita(titolo);
			int disponibiltaVecchia = gest.checkDisponibilita(titolo);
			gest.updateLibri(titolo, quantita, quantitaVecchia, disponibiltaVecchia);
			req.setAttribute("listaLibri", gest.stampaLibri());
			req.setAttribute("messaggio", "quantità e disponibilità aggiornate");
			gest.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		req.getRequestDispatcher("riordina.jsp").forward(req, resp);
	}
}
