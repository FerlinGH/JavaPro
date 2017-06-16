package net.ukr.grygorenko_d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean isLoggedIn = false;
		try {
			while (!isLoggedIn) {
				isLoggedIn = loginAttempt(scan);
			}
			showGreetings();

			while (!Utils.isLogOut()) {
				Thread thr = new Thread(new ReadMessagesThread());
				thr.start();
				System.out.println("Enter your message: ");
				while (true) {
					String text = scan.nextLine();
					if (text.isEmpty()) {
						logout();
						thr.interrupt();
						break;
					} else {
						if (text.equals("@status")) {
							setStatus(scan);
							continue;
						} else if (text.equals("@users")) {
							showUsers();
							continue;
						} else if (text.equals("@room")) {
							boolean roomChanged = setRoom(scan);
							if (roomChanged) {
								thr.interrupt();
								break;
							} else {
								continue;
							}
						}
						int res = sendMessage(text);
						if (res != 200) { // 200 OK
							System.out.println("HTTP error occured: " + res);
							return;
						}
					}
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			scan.close();
		}
	}

	private static boolean loginAttempt(Scanner scan) throws IOException {
		System.out.println("Enter your login: ");
		String login = scan.nextLine();
		System.out.println("Enter your password: ");
		String pass = scan.nextLine();
		URL url = new URL(Utils.getURL() + "/login?login=" + login + "&pass=" + pass);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		int res = conn.getResponseCode();
		if (res == 200) {
			System.out.println(String.format("Welcome to the chat, %s!", login));
			Utils.setLogin(login);
			Utils.setRoom("Main");
			Utils.setLogOut(false);
			return true;
		} else {
			System.out.println("Invalid login or password, please try again.");
			return false;
		}
	}

	private static void showGreetings() {
		System.out.println("You are now in Main chat, your status is ONLINE.");
		System.out.println("To change your status, type @status and press ENTER.");
		System.out.println("To see all users in the chat, type @users");
		System.out.println("To enter another chat room, enter @room");
		System.out.println("Enter empty message to quit.");
	}

	private static void logout() throws IOException {
		URL url = new URL(Utils.getURL() + "/status?login=" + Utils.getLogin() + "&status=LOGOUT");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		int res = conn.getResponseCode();
		if (res == 200) {
			if (Utils.getRoom().equals("Main")) {
				Utils.setLogOut(true);
				System.out.println("Bye-bye!");
				return;
			} else {
				Utils.setRoom("Main");
				System.out.println("You are back in Main Chat.");
				return;
			}
		} else {
			System.out.println("Error during logout.");
			return;
		}
	}

	private static void setStatus(Scanner scan) throws IOException {
		System.out.println("Enter your new status (f.e. AVAILABLE, BUSY, DND) :");
		String status = scan.nextLine();
		URL url = new URL(Utils.getURL() + "/status?login=" + Utils.getLogin() + "&status=" + status);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		int res = conn.getResponseCode();
		if (res == 200) {
			System.out.println("Status changed to " + status);
		} else {
			System.out.println("Could not change your status.");
		}
	}

	private static void showUsers() throws IOException {
		URL url = new URL(Utils.getURL() + "/users");
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()))) {
			String text = "";
			while ((text = br.readLine()) != null) {
				sb.append(text).append(System.lineSeparator());
			}
		}
		System.out.println("----Currently in chat-----");
		System.out.println(sb.toString());
		System.out.println("Enter your message:");
	}

	private static boolean setRoom(Scanner scan) throws IOException {
		System.out.println("Chatroom change requested. ");
		System.out.println("Enter \"@show\" to se all available chatrooms, then enter room's name.");
		System.out.println("New room will be created automatically.");
		System.out.println("To stay in your current room, simply press ENTER.");
		while (true) {
			String choice = scan.nextLine();
			if (choice.isEmpty()) {
				System.out.println("You are back in Main Chat.");
				return false;
			} else if (choice.equals("@show")) {
				showRooms();
				continue;
			} else {
				Utils.setRoom(choice);
				URL url = new URL(Utils.getURL() + "/rooms?login=" + Utils.getLogin() + "&room=" + Utils.getRoom());
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("POST");
				int res = con.getResponseCode();
				if (res == 200) {
					System.out.println("You are now in " + Utils.getRoom() + " chatroom.");
					System.out.println("To go back to Main chat, press ENTER any time.");
					break;
				} else {
					System.out.println("HTTP error in changeroom.");
					return false;
				}
			}
		}
		return true;
	}

	private static void showRooms() throws IOException {
		URL url = new URL(Utils.getURL() + "/rooms");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
			String text = "";
			while ((text = br.readLine()) != null) {
				sb.append(text).append(System.lineSeparator());
			}
		}
		System.out.println("----Currently available chatrooms-----");
		System.out.println(sb.toString());
	}

	public static int sendMessage(String text) throws IOException {
		String[] words = text.split(" ");
		Message m = null;
		if (words[0].startsWith("@")) {
			String to = words[0].substring(1);
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i < words.length; i++) {
				sb.append(words[i]).append(" ");
			}
			m = new Message(Utils.getLogin(), to, sb.toString());
		} else {
			m = new Message(Utils.getLogin(), "All", text);
		}
		int res = m.send(Utils.getURL() + "/add");
		return res;
	}
}
