package bibliotecaWebApp.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bibliotecaWebApp.repository.GestioneDb;
import bibliotecaWebApp.service.EmailUtility;

@WebServlet(urlPatterns = "/admin/auto")
public class InvioMailAutomatica extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("opzioniBiblioteca.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String indirizzo = req.getParameter("Utente");
		String oggetto = "Sollecitazione";
		String contenuto = "La esortiamo a restituire il libro "+req.getParameter("titolo")+" che andava consegnato in data "
				+ calcoloData(req.getParameter("data"));
		String risultatoMessaggio = "";

		try {
			EmailUtility.sendEmail(indirizzo, oggetto, contenuto);
			risultatoMessaggio = "Mail inviata correttamente";
		} catch (Exception ex) {
			ex.printStackTrace();
			risultatoMessaggio = "C'è stato un errore nell'invio della mail: " + ex.getMessage();
		} finally {
			req.setAttribute("messaggio", risultatoMessaggio);
			req.getRequestDispatcher("/messaggio.jsp").forward(req, resp);
		}
	}

	public String calcoloData(String dataInizio) {
		String[] d = dataInizio.split("/");
		System.out.println(d[0] + " , " + d[1] + " , " + d[2]);
		Calendar data1 = Calendar.getInstance();

		data1.set(Calendar.DATE, Integer.parseInt(d[0]));
		data1.set(Calendar.MONTH, Integer.parseInt(d[1]));
		data1.set(Calendar.YEAR, Integer.parseInt(d[2]));

		data1.add(Calendar.DATE, 30);

		return data1.get(Calendar.DATE) + "/" + data1.get(Calendar.MONTH) + "/" + data1.get(Calendar.YEAR);

	}
}
