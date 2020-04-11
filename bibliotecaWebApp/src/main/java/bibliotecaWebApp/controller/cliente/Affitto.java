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



@WebServlet (name = "affitto", urlPatterns = { "/cliente/affitto" })			
public class Affitto extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String azione = req.getParameter("azione");
		String titolo = req.getParameter("titolo");

		GestioneDb db;

		if (azione.equalsIgnoreCase("Aggiungi al carrello")) {
			try {

				db = new GestioneDb();
				if (!req.getParameter("quantita").equals("") && req.getParameter("quantita") != null) {
					String parameter = req.getParameter("quantita");
					int quantita = Integer.parseInt(parameter);
					if (req.getParameter("idTessera") == null) {
						// crea lo scontrino quando non c'è e aggiungi un prodotto al carrello
						int idNuovo = db.creaTessera(username);
						if (db.controlloQuantitaLibri(titolo, quantita, db.stampaLibri())) {
							db.inserimentoTabellaAffitto(titolo, username, idNuovo, quantita);
							db.updateDisponibilitaLibri(titolo, quantita);

							req.setAttribute("idTessera", idNuovo);
							//req.setAttribute("username", username);
							req.setAttribute("listaLibri", db.stampaLibri());
							req.setAttribute("mess", "Libro aggiunto con successo");
						} else {
							// non ci sono sufficienti libri
							req.setAttribute("idTessera", idNuovo);
							//req.setAttribute("username", username);
							req.setAttribute("listaLibri", db.stampaLibri());
							req.setAttribute("mess", "Quantità libri non disponibile");
						}
						db.close();
						req.getRequestDispatcher("/affitto.jsp").forward(req, resp);
					} else {
						// quando lo scontrino già esiste e aggiungi un prodotto
						String idSc = req.getParameter("idTessera");
						int idTessera = Integer.parseInt(idSc);
						if (db.controlloQuantitaLibri(titolo, quantita, db.stampaLibri())) {
							db.inserimentoTabellaAffitto(titolo, username, idTessera, quantita);
							db.updateDisponibilitaLibri(titolo, quantita);

							req.setAttribute("idTessera", idTessera);
							//req.setAttribute("username", username);
							req.setAttribute("listaLibri", db.stampaLibri());
							req.setAttribute("mess", "Libro aggiunto con successo");
						} else {
							// scontrino già esiste e non c'è quantità sufficiente
							req.setAttribute("idTessera", idTessera);
							//req.setAttribute("username", username);
							req.setAttribute("listaLibri", db.stampaLibri());
							req.setAttribute("mess", "Quantità libri non disponibile");
						}
						db.close();
						req.getRequestDispatcher("/affitto.jsp").forward(req, resp);

					}
				} else {
					// quando non esiste lo scontrino e premi aggiungi senza scrivere nulla
					if (req.getParameter("idTessera") == null) {
						//req.setAttribute("username", username);
						req.setAttribute("listaLibri", db.stampaLibri());
						req.setAttribute("mess", "Non puoi aggiungere 0 prodotti!");
						db.close();
						req.getRequestDispatcher("/affitto.jsp").forward(req, resp);
					} else {
						// lo scontrino esiste e premi aggiungi senza scrivere nulla
						String idSc = req.getParameter("idTessera");
						int idTessera = Integer.parseInt(idSc);
						req.setAttribute("idTessera", idTessera);
						//req.setAttribute("username", username);
						req.setAttribute("listaLibri", db.stampaLibri());
						req.setAttribute("mess", "Non puoi aggiungere 0 prodotti!");
						db.close();
						req.getRequestDispatcher("/affitto.jsp").forward(req, resp);

					}
				}
			} catch (ClassNotFoundException | IOException | SQLException e) {

				e.printStackTrace();
			}
		} else if (azione.equalsIgnoreCase("Affitta")) {
			try {
				db = new GestioneDb();

				if (req.getParameter("quantita") != null && !req.getParameter("quantita").equals("")) {
					String parameter = req.getParameter("quantita");
					int quantita = Integer.parseInt(parameter);
					if (req.getParameter("idTessera") == null) {
						// crea lo scontrino quando non c'è e aggiungi un prodotto al carrello e pagare
						int idNuovo = db.creaTessera(username);
						if (db.controlloQuantitaLibri(titolo, quantita, db.stampaLibri())) {
							db.inserimentoTabellaAffitto(titolo, username, idNuovo, quantita);;
							db.updateQuantitaLibri(titolo, quantita);

							req.setAttribute("idTessera", idNuovo);
							//req.setAttribute("username", username);
							req.setAttribute("listaLibri", db.stampaLibri());
							req.setAttribute("mess", "Libro aggiunto con successo e pagamento effettuato");
							db.close();
							req.getRequestDispatcher("/opzioniCliente.jsp").forward(req, resp);
						} else {
							// non ci sono sufficienti libri
							req.setAttribute("idTessera", idNuovo);
							//req.setAttribute("username", username);
							req.setAttribute("listaLibri", db.stampaLibri());
							req.setAttribute("mess", "Quantità libri non disponibile");
							db.close();
							req.getRequestDispatcher("/affitto.jsp").forward(req, resp);
						}

					} else {
						// scontrino già esiste e paghi direttamente un prodotto insieme anche ai
						// precedenti
						String idSc = req.getParameter("idTessera");
						int idTessera = Integer.parseInt(idSc);
						if (db.controlloQuantitaLibri(titolo, quantita, db.stampaLibri())) {
							db.inserimentoTabellaAffitto(titolo, username, idTessera, quantita);
							db.updateDisponibilitaLibri(titolo, quantita);

							req.setAttribute("idTessera", idTessera);
							//req.setAttribute("username", username);
							req.setAttribute("listaLibri", db.stampaLibri());
							req.setAttribute("mess", "Libro aggiunto con successo");
							db.close();
							req.getRequestDispatcher("/opzioniCliente.jsp").forward(req, resp);
						} else {
							// scontrino già esiste e non c'è sufficiente quantità
							req.setAttribute("idTessera", idTessera);
							//req.setAttribute("username", username);
							req.setAttribute("listaLibri", db.stampaLibri());
							req.setAttribute("mess", "Quantità libri non disponibile");
							db.close();
							req.getRequestDispatcher("/affitto.jsp").forward(req, resp);
						}
					}
				} else {
					// quando non esiste lo scontrino e premi paga senza scrivere nulla
					if (req.getParameter("idTessera") == null) {
						//req.setAttribute("username", username);
						req.setAttribute("listaLibri", db.stampaLibri());
						req.setAttribute("mess", "Non puoi prenotare 0 prodotti!");
						db.close();
						req.getRequestDispatcher("/affitto.jsp").forward(req, resp);
					} else {
						// quando non scrivi nulla, lo scontrino già esiste ma Paghi lo stesso tutto
						// quello che c'è nel carrello
						String idSc = req.getParameter("idTessera");
						int idTessera = Integer.parseInt(idSc);
						req.setAttribute("idTessera", idTessera);
						//req.setAttribute("username", username);
						req.setAttribute("listaLibri", db.stampaLibri());
						req.setAttribute("mess", "Pagamento effettuato con successo");
						db.close();
						req.getRequestDispatcher("/opzioniCliente.jsp").forward(req, resp);
					}
				}

			} catch (SQLException e) {

				e.printStackTrace();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
		} else if (azione.equalsIgnoreCase("Torna Indietro")) {
			try {

				if (req.getParameter("idTessera") == null) {
					//req.setAttribute("username", username);
					req.getRequestDispatcher("/opzioniCliente.jsp").forward(req, resp);
				} else {
					String idSc = req.getParameter("idTessera");
					int idTessera = Integer.parseInt(idSc);
					req.setAttribute("idTessera", idTessera);
					//req.setAttribute("username", username);
					db = new GestioneDb();
					db.cancellaTesseraVuoto(idTessera, username);
					db.cancellaPrestitoVuoto(idTessera, username);
					db.close();
					req.getRequestDispatcher("/opzioniCliente.jsp").forward(req, resp);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	}
