package bibliotecaWebApp.controller.cliente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bibliotecaWebApp.repository.GestioneDb;

@WebServlet(name = "opzioniCliente", urlPatterns = { "cliente/opzioniCliente, /opzioniCliente" })
public class GestioneCliente extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		GestioneDb db;
		try {

			//String username = req.getParameter("username");
			String azione = req.getParameter("action");
			HttpSession session = req.getSession();
			String username = (String) session.getAttribute("username");
			db = new GestioneDb();
			if (azione.equalsIgnoreCase("Compra libri")) {
				//int idScontrino = db.creaScontrino(username);
				req.setAttribute("listaLibri", db.stampaLibri());
				//req.setAttribute("idScontrino", idScontrino);
				//session.setAttribute("username", username);
				db.close();
				req.getRequestDispatcher("/acquisto.jsp").forward(req, resp);
			} else if (azione.equalsIgnoreCase("Affitta libri")) {
				//session.setAttribute("username", username);
				//int idTessera = db.creaTessera(username);
				//req.setAttribute("idTessera", idTessera);
				req.setAttribute("listaLibri", db.stampaLibri());
				db.close();
				req.getRequestDispatcher("/affitto.jsp").forward(req, resp);
			} else if (azione.equalsIgnoreCase("Stampa acquisti")) {
				//req.setAttribute("username", username);
				req.setAttribute("listaScontrini", db.stampaScontrini(username));
				db.close();
				req.getRequestDispatcher("/stampaAcquisti.jsp").forward(req, resp);
			} else if (azione.equalsIgnoreCase("Stampa prestiti")) {
				//req.setAttribute("username", username);
				req.setAttribute("listaPrestiti", db.stampaPrestiti(username));
				db.close();
				req.getRequestDispatcher("/stampaAffitti.jsp").forward(req, resp);
			} else if (azione.equalsIgnoreCase("Dettagli")) {
				//req.setAttribute("username", username);
				int idScontrino = Integer.parseInt(req.getParameter("id"));
				req.setAttribute("id", idScontrino);
				req.setAttribute("listaScontrini", db.stampaScontrini(username));
				req.setAttribute("listaProdottiDelloScontrino", db.stampaProdottiScontrino(idScontrino));
				db.close();
				req.getRequestDispatcher("/listaProdottiDelloScontrino.jsp").forward(req, resp);
			} else if (azione.equalsIgnoreCase("Info")) {
				//req.setAttribute("username", username);
				int idTessera = Integer.parseInt(req.getParameter("id"));
				req.setAttribute("id", idTessera);
				req.setAttribute("listaPrestiti", db.stampaPrestiti(username));
				req.setAttribute("listaLibriTessera", db.stampaLibriInPrestito(idTessera));
				db.close();
				req.getRequestDispatcher("/listaLibriTessera.jsp").forward(req, resp);
			} else if (azione.equalsIgnoreCase("Restituisci")) {
				String titolo = req.getParameter("titolo");
				int quantita = Integer.parseInt(req.getParameter("disp"));
				//update tabella libro
				db.updateTabellaLibroDopoRestituzione(titolo, quantita);
				//rimozione libri da prestito
				int idPrestito = Integer.parseInt(req.getParameter("prestito"));
				db.rimuoviLibroInPrestito(idPrestito);
				//remove prestito da cliente
				req.setAttribute("titolo", titolo);
				req.setAttribute("disp", quantita);
				req.setAttribute("listaPrestiti", db.stampaPrestiti(username));
				req.setAttribute("mess", "libro restituito con successo");
				db.close();
				req.getRequestDispatcher("/stampaAffitti.jsp").forward(req, resp);
			}
			else if (azione.equalsIgnoreCase("Home")) {
				//req.setAttribute("username", username);
				db.close();
				req.getRequestDispatcher(req.getContextPath() + "/opzioniCliente.jsp").forward(req, resp);
			}else if (azione.equalsIgnoreCase("Visualizza immagine")) {
				req.setAttribute("utente", db.prendiImmagine(username));
				//req.setAttribute("username", username);
				db.close();
				req.getRequestDispatcher("/profilo.jsp").forward(req, resp);
			}
			else if (azione.equalsIgnoreCase("Logout")) {
				session.invalidate();
				db.close();
				resp.sendRedirect(req.getContextPath() + "/");
			}


		} catch (ClassNotFoundException | IOException | SQLException e) {

			e.printStackTrace();
		}
	}
}
