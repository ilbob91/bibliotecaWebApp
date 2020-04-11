package bibliotecaWebApp.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bibliotecaWebApp.model.Utente;
import bibliotecaWebApp.repository.GestioneDb;
import bibliotecaWebApp.service.EmailUtility;

@WebServlet(urlPatterns = { "/registrazione" })
@MultipartConfig
public class Registrazione extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("messaggio", "Pagina non accessibile");
		req.getRequestDispatcher("home.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Part imagePart = req.getPart("image");

		GestioneDb gest = null;
		try {
			gest = new GestioneDb();

			if (gest.checkEmail(username) && !username.equals("admin")) {
				Utente utente = gest.salvaUtente(username, password, imagePart.getInputStream());
				EmailUtility.sendEmail(utente.getUsername(), "Conferma Mail", generaLinkValidazioneUtente(utente));
				gest.close();
				req.setAttribute("messaggio",
						"Ti è stata mandata una mail, clicca il link contenuto in essa per confermare la registrazione");
				req.getRequestDispatcher("login.jsp").forward(req, resp);

			} else {
					req.setAttribute("messaggio", "mail già presente, fai il Login");
					req.getRequestDispatcher("login.jsp").forward(req, resp);
				}

			
		} catch (ClassNotFoundException | SQLException | MessagingException e) {

		}

	}

	private String generaLinkValidazioneUtente(Utente utente) {
		String validationPath = "http://localhost:8080/bibliotecaWebApp/validazione?utente=";
		return "Per attivare la mail clicca su questo link: " + validationPath + utente.getUsername();
	}

}
