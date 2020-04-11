package bibliotecaWebApp.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bibliotecaWebApp.model.Utente;
import bibliotecaWebApp.repository.GestioneDb;
import bibliotecaWebApp.service.EmailUtility;

@WebServlet(name = "login", urlPatterns = "/login", initParams = { @WebInitParam(name = "username", value = "admin"),
		@WebInitParam(name = "password", value = "1234") })
public class Login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		GestioneDb gest;
            String user = req.getParameter("username");
			String pass = req.getParameter("password");
			HttpSession session = req.getSession();
		
			try {
				gest = new GestioneDb();
			
			
			Utente ut = gest.getUtente(user, pass);

			

		        if (user.equals(getInitParameter("username")) && pass.equals(getInitParameter("password"))) {
					req.getRequestDispatcher("opzioniBiblioteca.jsp").forward(req, resp);

				} else if (!gest.controlloUtente(user, pass)) {
					req.setAttribute("messaggio", "mail o password errata. Riprova oppure REGISTRATI");
					req.getRequestDispatcher("login.jsp").forward(req, resp);

				} else {
					if (!ut.isActive()) {
						req.setAttribute("messaggio", scriviRispostaUtenteNonAttivo(ut));
						gest.close();
						req.getRequestDispatcher("login.jsp").forward(req, resp);
					} else {
						session.setAttribute("username", ut.getUsername());
						gest.close();
						req.getRequestDispatcher("opzioniCliente.jsp").forward(req, resp);
					}
				}
		
			
			/*	if (gest.checkEmail(user) && !user.equals(getInitParameter("username"))) {
					gest.insertUtente(user, pass);
					EmailUtility.sendEmail(user, "Conferma Mail", generaLinkValidazioneUtente(user));
					gest.close();
					req.setAttribute("messaggio",
							"Ti è stata mandata una mail, clicca il link contenuto in essa per confermare la registrazione");
					req.getRequestDispatcher("login.jsp").forward(req, resp);

				} else {
					if (user.equals(getInitParameter("username"))) {
						req.setAttribute("messaggio", "Per entrare come amministratore premi ENTRA");
						req.getRequestDispatcher("login.jsp").forward(req, resp);
					} else {
						req.setAttribute("messaggio", "mail già presente, fai il Login");
						req.getRequestDispatcher("login.jsp").forward(req, resp);
					}

				}*/
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
	

	private String generaLinkValidazioneUtente(String utente) {
		String validationPath = "http://localhost:8080/bibliotecaWebApp/validazione?utente=";
		return "Per attivare la mail clicca su questo link: " + validationPath + utente;
	}

	private String scriviRispostaUtenteNonAttivo(Utente utente) {
		String mailUtente = utente.getUsername();
		int indexOf = mailUtente.indexOf('@');
		String parteFinaleMail = mailUtente.substring(indexOf);

		String primiDueCaratteri = mailUtente.substring(0, 3);
		String mailFinale = primiDueCaratteri + contaX(indexOf - 2) + parteFinaleMail;
		return "L'utente " + mailFinale + " non ha ancora validato l'email";
	}

	private String contaX(int numeri) {
		String x = "";
		for (int i = 0; i < numeri; i++) {
			x += "*";
		}
		return x;
	}

}
