package bibliotecaWebApp.controller.cliente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bibliotecaWebApp.repository.GestioneDb;

@WebServlet(name = "opzioniCliente", urlPatterns = { "/opzioniCliente" })
public class GestioneCliente extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		GestioneDb db;
		try {

			String username = req.getParameter("username");
			String azione = req.getParameter("action");
			db = new GestioneDb();
			if (azione.equalsIgnoreCase("Compra libri")) {
				//int idScontrino = db.creaScontrino(username);
				req.setAttribute("listaLibri", db.stampaLibri());
				//req.setAttribute("idScontrino", idScontrino);
				req.setAttribute("username", username);
				db.close();
				req.getRequestDispatcher("acquisto.jsp").forward(req, resp);
			} else if (azione.equalsIgnoreCase("Affitta libri")) {
				req.setAttribute("username", username);
				//int idTessera = db.creaTessera(username);
				//req.setAttribute("idTessera", idTessera);
				req.setAttribute("listaLibri", db.stampaLibri());
				db.close();
				req.getRequestDispatcher("affitto.jsp").forward(req, resp);
			} else if (azione.equalsIgnoreCase("Stampa acquisti")) {
				req.setAttribute("username", username);
				req.setAttribute("listaScontrini", db.stampaScontrini(username));
				db.close();
				req.getRequestDispatcher("stampaAcquisti.jsp").forward(req, resp);
			} else if (azione.equalsIgnoreCase("Stampa prestiti")) {
				req.setAttribute("username", username);
				req.setAttribute("listaPrestiti", db.stampaPrestiti(username));
				db.close();
				req.getRequestDispatcher("stampaAffitti.jsp").forward(req, resp);
			} else if (azione.equalsIgnoreCase("Dettagli")) {
				req.setAttribute("username", username);
				int idScontrino = Integer.parseInt(req.getParameter("id"));
				req.setAttribute("id", idScontrino);
				req.setAttribute("listaScontrini", db.stampaScontrini(username));
				req.setAttribute("listaProdottiDelloScontrino", db.stampaProdottiScontrino(idScontrino));
				db.close();
				req.getRequestDispatcher("listaProdottiDelloScontrino.jsp").forward(req, resp);
			} else if (azione.equalsIgnoreCase("Info")) {
				req.setAttribute("username", username);
				int idTessera = Integer.parseInt(req.getParameter("id"));
				req.setAttribute("id", idTessera);
				req.setAttribute("listaPrestiti", db.stampaPrestiti(username));
				req.setAttribute("listaLibriTessera", db.stampaLibriInPrestito(idTessera));
				db.close();
				req.getRequestDispatcher("listaLibriTessera.jsp").forward(req, resp);
			}

		} catch (ClassNotFoundException | IOException | SQLException e) {

			e.printStackTrace();
		}
	}
}
