package net.ukr.grygorenko_d;

public class ChatRoom {
	private String name;
	private MessageList messageList;

	public ChatRoom(String name) {
		super();
		this.name = name;
		this.messageList = new MessageList();
	}

	public ChatRoom() {
		super();
	}

	public String getName() {
		return name;
	}

	public MessageList getMessageList() {
		return messageList;
	}

	public void setMessageList(MessageList messageList) {
		this.messageList = messageList;
	}

	public String toString() {
		int i = 0;
		for (User user : UserList.getInstance().getList()) {
			if (user.getRoom().equals(name)) {
				i++;
			}
		}
		return new StringBuilder().append("Chatroom ").append(name).append(" (").append(i).append(" users online).")
				.toString();

	}

}
