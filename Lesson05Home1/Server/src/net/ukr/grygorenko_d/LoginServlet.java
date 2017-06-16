package net.ukr.grygorenko_d;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, String> credentials = new HashMap<>();

	public LoginServlet() {
		super();
		credentials.put("User1", "qaz");
		credentials.put("User2", "wsx");
		credentials.put("User3", "edc");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");

		if (credentials.containsKey(login)) {
			if (credentials.get(login).equals(pass)) {
				UserList.getInstance().addUser(new User(login, "ONLINE", "Main"));
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}

	}

}
