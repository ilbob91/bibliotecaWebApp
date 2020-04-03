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
@WebServlet(name = "aggiungi", urlPatterns = "/Aggiungi")
public class AggiungiLibro extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setAttribute("messaggio", "hai tentato di accedere manualmente all'aggiunta di un libro");
	req.getRequestDispatcher("opzioniBiblioteca.jsp").forward(req, resp);
}

@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String titolo = req.getParameter("titolo");
		String autore = req.getParameter("autore");
		double prezzo = Double.parseDouble(req.getParameter("prezzo"));
		int disponibilita = Integer.parseInt(req.getParameter("disponibilita"));
		int quantita = Integer.parseInt(req.getParameter("quantita"));
		Libro l  = new Libro(titolo, autore, prezzo, disponibilita, quantita);
		try {
			GestioneDb gest = new GestioneDb();
			req.setAttribute("listaLibri", gest.getLibri(l));
			gest.close();
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
	}
		req.getRequestDispatcher("lista_libri.jsp").forward(req, resp);
}
}