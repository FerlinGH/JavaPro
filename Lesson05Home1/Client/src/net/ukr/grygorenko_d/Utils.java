package net.ukr.grygorenko_d;

public class Utils {
	private static String URL = "http://127.0.0.1";
	private static int PORT = 8080;
	private static String descriptor = "/Chat_Server";
	private static String login = null;
	private static String room;
	private static boolean logOut;

	public static String getURL() {
		return URL + ":" + PORT + descriptor;
	}

	public static String getLogin() {
		return login;
	}

	public static void setLogin(String login) {
		Utils.login = login;
	}

	public static String getRoom() {
		return room;
	}

	public static void setRoom(String room) {
		Utils.room = room;
	}

	public static boolean isLogOut() {
		return logOut;
	}

	public static void setLogOut(boolean logOut) {
		Utils.logOut = logOut;
	}

}
