package bibliotecaWebApp.controller.cliente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bibliotecaWebApp.repository.GestioneDb;

@WebServlet(name = "acquisto", urlPatterns = { "/acquisto" })
public class Acquisto extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		int idScontrino = Integer.parseInt(req.getParameter("idScontrino"));
		String azione = req.getParameter("azione");
		String titolo = req.getParameter("titolo");
		int quantita = Integer.parseInt(req.getParameter("quantita"));

		GestioneDb db;

		if (azione.equalsIgnoreCase("Aggiungi al carrello")) {

			try {

				db = new GestioneDb();
				if (db.controlloQuantitaLibri(titolo, quantita, db.stampaLibri())) {
					db.inserimentoTabellaAcquisto(titolo, quantita, username, idScontrino);
					db.updateQuantitaLibri(titolo, quantita);

					req.setAttribute("idScontrino", idScontrino);
					req.setAttribute("username", username);
					req.setAttribute("listaLibri", db.stampaLibri());
					req.setAttribute("mess", "Libro aggiunto con successo");
				} else {
					req.setAttribute("idScontrino", idScontrino);
					req.setAttribute("username", username);
					req.setAttribute("listaLibri", db.stampaLibri());
					req.setAttribute("mess", "Quantità libri non disponibile");
				}
				db.close();
				req.getRequestDispatcher("acquisto.jsp").forward(req, resp);

			} catch (ClassNotFoundException | IOException | SQLException e) {

				e.printStackTrace();
			}
		} else if (azione.equalsIgnoreCase("Paga")) {
			try {
				db = new GestioneDb();
				if (req.getParameter("titolo") != null && !req.getParameter("titolo").equals("")) {
					if (db.controlloQuantitaLibri(titolo, quantita, db.stampaLibri())) {
						db.inserimentoTabellaAcquisto(titolo, quantita, username, idScontrino);
						db.updateQuantitaLibri(titolo, quantita);

						req.setAttribute("idScontrino", idScontrino);
						req.setAttribute("username", username);
						req.setAttribute("listaLibri", db.stampaLibri());
						req.setAttribute("mess", "Libro aggiunto con successo");
						double spesa = db.getPrezzo(idScontrino);
						db.totaleScontrino(idScontrino, spesa);

					} else {
						req.setAttribute("idScontrino", idScontrino);
						req.setAttribute("username", username);
						req.setAttribute("listaLibri", db.stampaLibri());
						req.setAttribute("mess", "Quantità libri non disponibile");
					}
				}
				double spesa = db.getPrezzo(idScontrino);
				db.totaleScontrino(idScontrino, spesa);
				db.close();

			} catch (SQLException e) {

				e.printStackTrace();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
			req.setAttribute("username", username);
			req.setAttribute("idScontrino", idScontrino);
			req.getRequestDispatcher("opzioniCliente.jsp").forward(req, resp);
		}
	}
}
