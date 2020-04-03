package bibliotecaWebApp.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bibliotecaWebApp.repository.GestioneDb;

@WebServlet(urlPatterns = { "/validazione" })
public class ValidationController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mailUtente = req.getParameter("utente");
		GestioneDb gestione;
		try {
			gestione = new GestioneDb();
			gestione.validaUtente(mailUtente);
			gestione.close();
			req.setAttribute("messaggio", scriviRispostaUtenteAttivato(mailUtente));
			req.getRequestDispatcher("login.jsp").forward(req, resp);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	} private String scriviRispostaUtenteAttivato(String mailUtente) {
		
		int indexOf = mailUtente.indexOf('@');
		String parteFinaleMail = mailUtente.substring(indexOf);

		String primiDueCaratteri = mailUtente.substring(0, 3);
		String mailFinale = primiDueCaratteri + contaX(indexOf - 2) + parteFinaleMail;
		return "L'utente " + mailFinale + " è stato validato";
	}

	private String contaX(int numeri) {
		String x = "";
		for (int i = 0; i < numeri; i++) {
			x += "*";
		}
		return x;
	}
}