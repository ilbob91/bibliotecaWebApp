package bibliotecaWebApp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bibliotecaWebApp.service.EmailUtility;

@WebServlet(urlPatterns = "/invioMailPrestito")
public class InvioMailPrestito extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String indirizzo = req.getParameter("indirizzo");
		String oggetto = req.getParameter("oggetto");
		String contenuto = req.getParameter("contenuto");
		String risultatoMessaggio = "";

		try {
			EmailUtility.sendEmail(indirizzo, oggetto, contenuto);
			risultatoMessaggio = "Mail inviata correttamente";
		} catch (Exception ex) {
			ex.printStackTrace();
			risultatoMessaggio = "C'è stato un errore nell'invio della mail: " + ex.getMessage();
		} finally {
			req.setAttribute("messaggio", risultatoMessaggio);
			getServletContext().getRequestDispatcher("/opzioniBiblioteca.jsp").forward(req, resp);
		}
	}
}
