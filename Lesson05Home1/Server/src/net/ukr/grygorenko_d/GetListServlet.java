package net.ukr.grygorenko_d;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GetList", urlPatterns = "/get")
public class GetListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MessageList msgList;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String room = req.getParameter("room");
		String fromStr = req.getParameter("from");
		int from = 0;
		try {
			from = Integer.parseInt(fromStr);
		} catch (NumberFormatException ex) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		for (ChatRoom chRoom : ChatRoomsList.getInstance().getRoomsList()) {
			if (chRoom.getName().equals(room)) {
				msgList = chRoom.getMessageList();
				break;
			}
		}

		String json = msgList.toJSON(from);
		if (json != null) {
			PrintWriter pw = resp.getWriter();
			pw.println(json);
		}
	}
}
