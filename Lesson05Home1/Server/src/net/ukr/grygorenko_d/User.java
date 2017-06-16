package net.ukr.grygorenko_d;

public class User {
	private String login;
	private String status;
	private String room;

	public User(String login, String status, String room) {
		super();
		this.login = login;
		this.status = status;
		this.room = room;
	}

	public User() {
		super();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", status=" + status + ", room=" + room + "]";
	}

}
