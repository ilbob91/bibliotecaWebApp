package bibliotecaWebApp.controller.cliente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bibliotecaWebApp.repository.GestioneDb;

@WebServlet(name = "acquisto", urlPatterns = { "/cliente/acquisto" })
public class Acquisto extends HttpServlet {
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
					if (req.getParameter("idScontrino") == null) {
						// crea lo scontrino quando non c'è e aggiungi un prodotto al carrello
						int idNuovo = db.creaScontrino(username);
						if (db.controlloQuantitaLibri(titolo, quantita, db.stampaLibri())) {
							db.inserimentoTabellaAcquisto(titolo, quantita, username, idNuovo);
							db.updateQuantitaLibri(titolo, quantita);

							req.setAttribute("idScontrino", idNuovo);
							//req.setAttribute("username", username);
							req.setAttribute("listaLibri", db.stampaLibri());
							req.setAttribute("mess", "Libro aggiunto con successo");
						} else {
							// non ci sono sufficienti libri
							req.setAttribute("idScontrino", idNuovo);
							//req.setAttribute("username", username);
							req.setAttribute("listaLibri", db.stampaLibri());
							req.setAttribute("mess", "Quantità libri non disponibile");
						}
						db.close();
						req.getRequestDispatcher("/acquisto.jsp").forward(req, resp);
					} else {
						// quando lo scontrino già esiste e aggiungi un prodotto
						String idSc = req.getParameter("idScontrino");
						int idScontrino = Integer.parseInt(idSc);
						if (db.controlloQuantitaLibri(titolo, quantita, db.stampaLibri())) {
							db.inserimentoTabellaAcquisto(titolo, quantita, username, idScontrino);
							db.updateQuantitaLibri(titolo, quantita);

							req.setAttribute("idScontrino", idScontrino);
							//req.setAttribute("username", username);
							req.setAttribute("listaLibri", db.stampaLibri());
							req.setAttribute("mess", "Libro aggiunto con successo");
						} else {
							// scontrino già esiste e non c'è quantità sufficiente
							req.setAttribute("idScontrino", idScontrino);
							//req.setAttribute("username", username);
							req.setAttribute("listaLibri", db.stampaLibri());
							req.setAttribute("mess", "Quantità libri non disponibile");
						}
						db.close();
						req.getRequestDispatcher("/acquisto.jsp").forward(req, resp);

					}
				} else {
					// quando non esiste lo scontrino e premi aggiungi senza scrivere nulla
					if (req.getParameter("idScontrino") == null) {
						//req.setAttribute("username", username);
						req.setAttribute("listaLibri", db.stampaLibri());
						req.setAttribute("mess", "Non puoi aggiungere 0 prodotti!");
						db.close();
						req.getRequestDispatcher("/acquisto.jsp").forward(req, resp);
					} else {
						// lo scontrino esiste e premi aggiungi senza scrivere nulla
						String idSc = req.getParameter("idScontrino");
						int idScontrino = Integer.parseInt(idSc);
						req.setAttribute("idScontrino", idScontrino);
						//req.setAttribute("username", username);
						req.setAttribute("listaLibri", db.stampaLibri());
						req.setAttribute("mess", "Non puoi aggiungere 0 prodotti!");
						db.close();
						req.getRequestDispatcher("/acquisto.jsp").forward(req, resp);

					}
				}
			} catch (ClassNotFoundException | IOException | SQLException e) {

				e.printStackTrace();
			}
		} else if (azione.equalsIgnoreCase("Paga")) {
			try {
				db = new GestioneDb();

				if (req.getParameter("quantita") != null && !req.getParameter("quantita").equals("")) {
					String parameter = req.getParameter("quantita");
					int quantita = Integer.parseInt(parameter);
					if (req.getParameter("idScontrino") == null) {
						// crea lo scontrino quando non c'è e aggiungi un prodotto al carrello e pagare
						int idNuovo = db.creaScontrino(username);
						if (db.controlloQuantitaLibri(titolo, quantita, db.stampaLibri())) {
							db.inserimentoTabellaAcquisto(titolo, quantita, username, idNuovo);
							db.updateQuantitaLibri(titolo, quantita);

							req.setAttribute("idScontrino", idNuovo);
							//req.setAttribute("username", username);
							req.setAttribute("listaLibri", db.stampaLibri());
							req.setAttribute("mess", "Libro aggiunto con successo e pagamento effettuato");
							double spesa = db.getPrezzo(idNuovo);
							db.totaleScontrino(idNuovo, spesa);
							db.close();
							req.getRequestDispatcher("/opzioniCliente.jsp").forward(req, resp);
						} else {
							// non ci sono sufficienti libri
							req.setAttribute("idScontrino", idNuovo);
							//req.setAttribute("username", username);
							req.setAttribute("listaLibri", db.stampaLibri());
							req.setAttribute("mess", "Quantità libri non disponibile");
							db.close();
							req.getRequestDispatcher("/acquisto.jsp").forward(req, resp);
						}

					} else {
						// scontrino già esiste e paghi direttamente un prodotto insieme anche ai
						// precedenti
						String idSc = req.getParameter("idScontrino");
						int idScontrino = Integer.parseInt(idSc);
						if (db.controlloQuantitaLibri(titolo, quantita, db.stampaLibri())) {
							db.inserimentoTabellaAcquisto(titolo, quantita, username, idScontrino);
							db.updateQuantitaLibri(titolo, quantita);

							req.setAttribute("idScontrino", idScontrino);
							//req.setAttribute("username", username);
							req.setAttribute("listaLibri", db.stampaLibri());
							req.setAttribute("mess", "Libro aggiunto con successo");
							double spesa = db.getPrezzo(idScontrino);
							db.totaleScontrino(idScontrino, spesa);
							db.close();
							req.getRequestDispatcher("/opzioniCliente.jsp").forward(req, resp);
						} else {
							// scontrino già esiste e non c'è sufficiente quantità
							req.setAttribute("idScontrino", idScontrino);
							//req.setAttribute("username", username);
							req.setAttribute("listaLibri", db.stampaLibri());
							req.setAttribute("mess", "Quantità libri non disponibile");
							db.close();
							req.getRequestDispatcher("/acquisto.jsp").forward(req, resp);
						}
					}
				} else {
					// quando non esiste lo scontrino e premi paga senza scrivere nulla
					if (req.getParameter("idScontrino") == null) {
						//req.setAttribute("username", username);
						req.setAttribute("listaLibri", db.stampaLibri());
						req.setAttribute("mess", "Non puoi pagare 0 prodotti!");
						db.close();
						req.getRequestDispatcher("/acquisto.jsp").forward(req, resp);
					} else {
						// quando non scrivi nulla, lo scontrino già esiste ma Paghi lo stesso tutto
						// quello che c'è nel carrello
						String idSc = req.getParameter("idScontrino");
						int idScontrino = Integer.parseInt(idSc);
						req.setAttribute("idScontrino", idScontrino);
						//req.setAttribute("username", username);
						req.setAttribute("listaLibri", db.stampaLibri());
						req.setAttribute("mess", "Pagamento effettuato con successo");
						double spesa = db.getPrezzo(idScontrino);
						db.totaleScontrino(idScontrino, spesa);
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

				if (req.getParameter("idScontrino") == null) {
					//req.setAttribute("username", username);
					req.getRequestDispatcher("/opzioniCliente.jsp").forward(req, resp);
				} else {
					String idSc = req.getParameter("idScontrino");
					int idScontrino = Integer.parseInt(idSc);
					req.setAttribute("idScontrino", idScontrino);
					//req.setAttribute("username", username);
					db = new GestioneDb();
					db.cancellaScontrinoVuoto(idScontrino, username);
					db.cancellaAcquistoVuoto(idScontrino, username);
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
