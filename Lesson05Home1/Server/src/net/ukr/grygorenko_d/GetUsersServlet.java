package net.ukr.grygorenko_d;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GetUsers", urlPatterns = "/users")
public class GetUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetUsersServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		for (User user : UserList.getInstance().getList()) {
			pw.println(user);
		}
		pw.println();
	}

}
