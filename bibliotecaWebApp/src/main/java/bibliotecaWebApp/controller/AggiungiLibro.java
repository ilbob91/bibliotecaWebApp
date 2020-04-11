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

@WebServlet(name = "aggiungi", urlPatterns = "/admin/aggiungi")
public class AggiungiLibro extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String azione = req.getParameter("action");
		
		if("aggiungi".equalsIgnoreCase(azione)) {
		try {
			String titolo = req.getParameter("titolo");
			String autore = req.getParameter("autore");
			double prezzo = Double.parseDouble(req.getParameter("prezzo"));
			int disponibilita = Integer.parseInt(req.getParameter("disponibilita"));
			int quantita = Integer.parseInt(req.getParameter("quantita"));
			Libro l = new Libro(titolo, autore, prezzo, disponibilita, quantita);
			GestioneDb gest = new GestioneDb();
			req.setAttribute("listaLibri", gest.getLibri(l));
			gest.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("/lista_libri.jsp").forward(req, resp);
	} else if ("Torna Indietro".equalsIgnoreCase(azione)){
		req.getRequestDispatcher("/opzioniBiblioteca.jsp").forward(req, resp);
}
	}
}