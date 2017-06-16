package net.ukr.grygorenko_d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ReadMessagesThread implements Runnable {
	private final Gson gson;
	private int receivedMess;

	public ReadMessagesThread() {
		gson = new GsonBuilder().create();
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				URL url = new URL(Utils.getURL() + "/get?room=" + Utils.getRoom() + "&from=" + receivedMess);
				HttpURLConnection http = (HttpURLConnection) url.openConnection();
				StringBuilder sb = new StringBuilder();
				try (BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()))) {
					String text = "";
					while ((text = br.readLine()) != null) {
						sb.append(text).append(System.lineSeparator());
					}
				}
				String strBuf = sb.toString();
				JsonMessages list = gson.fromJson(strBuf, JsonMessages.class);
				if (list != null) {
					for (Message m : list.getList()) {
						processMessage(m);
					}
				}
				Thread.sleep(500);
			}
		} catch (Exception ex) {
	//		ex.printStackTrace();
		}
	}

	private void processMessage(Message m) {
		if ((m.getTo().equals("All"))) {
			System.out.println(m);
			receivedMess++;
		} else if (m.getFrom().equals(Utils.getLogin())) {
			System.out.println("Private sent to " + m.getTo() + m);
			receivedMess++;
		} else if (m.getTo().equals(Utils.getLogin())) {
			System.out.println("Private received " + m);
			receivedMess++;
		} else {
			receivedMess++;
		}
	}
}
