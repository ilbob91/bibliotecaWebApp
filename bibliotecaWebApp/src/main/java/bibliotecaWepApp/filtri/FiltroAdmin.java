package bibliotecaWepApp.filtri;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "admin", urlPatterns = "/admin/*")
public class FiltroAdmin implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String loginURI = req.getContextPath() + "/";
		
		try {
		HttpSession session = (HttpSession) req.getSession();
		

		boolean loggedIn = session != null && session.getAttribute("username") != null && session.getAttribute("tipo").equals("admin");
		boolean loginRequest = req.getRequestURI().equals(loginURI);

		if (loggedIn || loginRequest) {
			System.out.println("sono nel filtro admin");
			chain.doFilter(request, response);
		} else {
			System.out.println("admin fabio72");
			resp.sendRedirect(loginURI);
		}
	}
	catch (NullPointerException | IOException | ServletException e) {
		resp.sendRedirect(loginURI);
		e.printStackTrace();
	}
	}

// if (session.getAttribute("utente") == null) {
//	resp.sendRedirect("/login.jsp");
// } else {

// }

	@Override
	public void destroy() {

	}

}


