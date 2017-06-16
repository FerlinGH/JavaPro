package net.ukr.grygorenko_d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Add", urlPatterns = "/add")
public class AddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MessageList msgList;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()))) {
			String text = "";
			while ((text = br.readLine()) != null) {
				sb.append(text).append(System.lineSeparator());
			}
		}
		String bufStr = sb.toString();
		Message msg = Message.fromJSON(bufStr);
		if (msg != null) {
			for (ChatRoom chRoom : ChatRoomsList.getInstance().getRoomsList()) {
				if (chRoom.getName().equals(msg.getRoom())) {
					msgList = chRoom.getMessageList();
					break;
				}
			}
			msgList.add(msg);
		} else {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

}
