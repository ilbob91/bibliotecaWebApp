package bibliotecaWebApp.controller.cliente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bibliotecaWebApp.repository.GestioneDb;



@WebServlet (name = "affitto", urlPatterns = { "/affitto" })			
public class Affitto extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		int idTessera = Integer.parseInt(req.getParameter("idTessera"));
		String azione = req.getParameter("azione");
		GestioneDb db;
		String titolo = req.getParameter("titolo");
		int quantita = Integer.parseInt(req.getParameter("quantita"));
		
		if (azione.equalsIgnoreCase("Aggiungi al carrello")) {
			
			try {
		
				db = new GestioneDb();
				if (db.controlloQuantitaLibri(titolo, quantita, db.stampaLibri())) {
					db.inserimentoTabellaAffitto(titolo, username, idTessera, quantita);
					db.updateDisponibilitaLibri(titolo, quantita);
				

					req.setAttribute("idTessera", idTessera);
				req.setAttribute("username", username);
				req.setAttribute("listaLibri", db.stampaLibri());
				req.setAttribute("mess", "Libro aggiunto con successo");
				} else {
					req.setAttribute("idTessera", idTessera);
					req.setAttribute("username", username);
					req.setAttribute("listaLibri", db.stampaLibri());
					req.setAttribute("mess", "Quantità libri non disponibile");
				}
				db.close();
				req.getRequestDispatcher("affitto.jsp").forward(req, resp);
				
			} catch (ClassNotFoundException | IOException | SQLException e) {

				e.printStackTrace();
			}
		} else if (azione.equalsIgnoreCase("Affitta")) {
			try {
				db = new GestioneDb();
				if (req.getParameter("titolo")!=null && !req.getParameter("titolo").equals("")) {
	                if (db.controlloQuantitaLibri(titolo, quantita, db.stampaLibri())) {
	                	db.inserimentoTabellaAffitto(titolo, username, idTessera, quantita);
						db.updateDisponibilitaLibri(titolo, quantita);
					

						req.setAttribute("idTessera", idTessera);
					req.setAttribute("username", username);
					req.setAttribute("listaLibri", db.stampaLibri());
					req.setAttribute("mess", "Libro aggiunto con successo");
					} else {
						req.setAttribute("idTessera", idTessera);
						req.setAttribute("username", username);
						req.setAttribute("listaLibri", db.stampaLibri());
						req.setAttribute("mess", "Quantità libri non disponibile");
	    			}
	            }
				db.close();
				
			} catch (SQLException e) {

				e.printStackTrace();
			} catch (ClassNotFoundException e) {
		
				e.printStackTrace();
			} req.setAttribute("username", username);
			req.setAttribute("idTessera", idTessera);
			req.getRequestDispatcher("paginaCliente.jsp").forward(req, resp);
		} 
	}
	}
