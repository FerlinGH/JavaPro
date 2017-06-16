package net.ukr.grygorenko_d;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomsList {
	private static ChatRoomsList chRoomLst = new ChatRoomsList();
	private List<ChatRoom> roomsList;

	private ChatRoomsList() {
		super();
		this.roomsList = new ArrayList<>();
		roomsList.add(new ChatRoom("Main"));
	}

	public static ChatRoomsList getInstance() {
		return chRoomLst;
	}

	public List<ChatRoom> getRoomsList() {
		return roomsList;
	}

}
