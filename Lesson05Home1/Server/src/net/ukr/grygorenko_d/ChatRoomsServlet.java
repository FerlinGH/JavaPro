package net.ukr.grygorenko_d;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ChatRooms", urlPatterns = "/rooms")
public class ChatRoomsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChatRoomsServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		for (ChatRoom chRoom : ChatRoomsList.getInstance().getRoomsList()) {
			pw.println(chRoom);
		}
		pw.println();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String room = request.getParameter("room");
		for (User u : UserList.getInstance().getList()) {
			if (u.getLogin().equals(login)) {
				u.setRoom(room);
				break;
			}
		}
		boolean contains = false;
		for (ChatRoom chR : ChatRoomsList.getInstance().getRoomsList()) {
			if (chR.getName().equals(room)) {
				contains = true;
				break;
			}
		}
		if (!contains) {
			ChatRoomsList.getInstance().getRoomsList().add(new ChatRoom(room));
		}
		response.setStatus(HttpServletResponse.SC_OK);

	}

}
