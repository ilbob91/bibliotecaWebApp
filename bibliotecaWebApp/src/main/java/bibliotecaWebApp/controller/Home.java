package bibliotecaWebApp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "home", urlPatterns = "/home", initParams = { @WebInitParam(name = "username", value = "admin"),
		@WebInitParam(name = "password", value = "1234") })
public class Home extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String azione = req.getParameter("azione");

		if ("Entra".equalsIgnoreCase(azione)) {
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		} else if ("Registrati".equalsIgnoreCase(azione)) {

			req.getRequestDispatcher("registrazione.jsp").forward(req, resp);
			/*
			 * if (gest.checkEmail(user) && !user.equals(getInitParameter("username"))) {
			 * gest.insertUtente(user, pass); EmailUtility.sendEmail(user, "Conferma Mail",
			 * generaLinkValidazioneUtente(user)); gest.close();
			 * req.setAttribute("messaggio",
			 * "Ti è stata mandata una mail, clicca il link contenuto in essa per confermare la registrazione"
			 * ); req.getRequestDispatcher("login.jsp").forward(req, resp);
			 * 
			 * } else { if (user.equals(getInitParameter("username"))) {
			 * req.setAttribute("messaggio", "Per entrare come amministratore premi ENTRA");
			 * req.getRequestDispatcher("login.jsp").forward(req, resp); } else {
			 * req.setAttribute("messaggio", "mail già presente, fai il Login");
			 * req.getRequestDispatcher("login.jsp").forward(req, resp); }
			 * 
			 * }
			 */
		}

	}
}