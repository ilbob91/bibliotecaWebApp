package bibliotecaWebApp.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bibliotecaWebApp.repository.GestioneDb;
@WebServlet(name = "gestione", urlPatterns = "/gestioneBiblioteca")
public class GestioneBiblioteca extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	req.setAttribute("messaggio", "hai tentato di accedere manualmente alla gestione biblioteca");
	req.getRequestDispatcher("opzioniBiblioteca.jsp").forward(req, resp);
	
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String azione = req.getParameter("azione");
	if (azione.equalsIgnoreCase("Aggiungi un libro")) {
		try {
			GestioneDb g = new GestioneDb();
			req.setAttribute("listaLibri", g.stampaLibri());
			g.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("aggiungi.jsp").forward(req, resp);;
	} else if (azione.equalsIgnoreCase("Riordina libro")) {
		try {
			GestioneDb g = new GestioneDb();
			req.setAttribute("listaLibri", g.stampaLibri());
			g.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("riordina.jsp").forward(req, resp);;
	} else if(azione.equalsIgnoreCase("Invia mail")) {
		req.getRequestDispatcher("invia_mail.jsp").forward(req, resp);;
	} else if (azione.equalsIgnoreCase("Stampa lista libri")) {
		try {
			GestioneDb g = new GestioneDb();
			req.setAttribute("listaLibri", g.stampaLibri());
			g.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("lista_libri.jsp").forward(req, resp);;
	}else if (azione.equalsIgnoreCase("Stampa lista libri venduti")) {
		try {
			GestioneDb g = new GestioneDb();
			req.setAttribute("listaLibriVenduti", g.stampaLibriVenduti());
			g.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("lista_libri_venduti.jsp").forward(req, resp);;
	}else if (azione.equalsIgnoreCase("Stampa lista libri prestati")) {
		try {
			GestioneDb g = new GestioneDb();
			req.setAttribute("listaLibriPrestati", g.stampaLibriPrestati());
			g.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("lista_libri_prestati.jsp").forward(req, resp);;
	}
	}
}
