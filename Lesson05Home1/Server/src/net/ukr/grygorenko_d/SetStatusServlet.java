package net.ukr.grygorenko_d;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Status", urlPatterns = "/status")
public class SetStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SetStatusServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String status = request.getParameter("status");

		Iterator<User> iter = UserList.getInstance().getList().iterator();
		while (iter.hasNext()) {
			User user = iter.next();
			if (user.getLogin().equals(login)) {
				if (status.equals("LOGOUT")) {
					if (user.getRoom().equals("Main")) {
						iter.remove();
						response.setStatus(HttpServletResponse.SC_OK);
					} else {
						user.setRoom("Main");
						response.setStatus(HttpServletResponse.SC_OK);
					}
				} else {
					int i = UserList.getInstance().getList().indexOf(user);
					UserList.getInstance().getList().get(i).setStatus(status);
					response.setStatus(HttpServletResponse.SC_OK);

				}

			}

		}
	}
}
