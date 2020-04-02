package bibliotecaWebApp.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bibliotecaWebApp.model.Utente;
import bibliotecaWebApp.repository.GestioneDb;
@WebServlet(name = "login", urlPatterns = {"/login" }, initParams = {
		@WebInitParam(name = "username", value = "admin"), @WebInitParam(name = "password", value = "1234") })
public class Login extends HttpServlet {

   @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   GestioneDb gest;
	 try {
		  gest = new GestioneDb();
	
		String user = req.getParameter("username");
		String pass = req.getParameter("password");
		Utente u = new Utente(user, pass);
		String azione = req.getParameter("azione");
	if(azione.equalsIgnoreCase("Entra")) {
		if (gest.controlloUtente(u)) {
			gest.close();
		 req.setAttribute("username", u.getUsername());
		 req.getRequestDispatcher("opzioniCliente.jsp");
		}
		else if (user.equals(getInitParameter("username")) && pass.equals(getInitParameter("password"))) {
			req.getRequestDispatcher("opzioniBiblioteca.jsp");
		} else {
			 req.setAttribute("messaggio", "mail o password errata. Riprova oppure REGISTRATI");
             req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	
		
		
	   } else if (azione.equalsIgnoreCase("Registrati")) {
		   if(u.getUsername().equals(getInitParameter("username"))) {
			   req.setAttribute("messaggio", "Per entrare come amministratore premi ENTRA");
			   req.getRequestDispatcher("login.jsp").forward(req, resp);
		   }
			   else if(gest.checkEmail(u)) {
			   gest.insertUtente(u);
			   gest.close();
			   req.setAttribute("username", u.getUsername());
			   req.getRequestDispatcher("opzioniCliente.jsp");
		   }
		      else {
			   req.setAttribute("messaggio", "mail già presente, fai il Login");
			   req.getRequestDispatcher("login.jsp").forward(req, resp);
		      }
			  
		   } 
			   

	   
   } catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }

}
