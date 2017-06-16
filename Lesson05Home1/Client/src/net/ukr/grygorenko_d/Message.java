package net.ukr.grygorenko_d;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Message {
	private Date date = new Date();
	private String from;
	private String to;
	private String text;
	private String room;

	public Message(String from, String to, String text) {
		this.from = from;
		this.to = to;
		this.text = text;
		this.room = Utils.getRoom();
	}

	public static Message fromJSON(String s) {
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(s, Message.class);
	}

	@Override
	public String toString() {
		return new StringBuilder().append("[").append(date).append(", From: ").append(from).append("] ").append(text)
				.toString();
	}

	public int send(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

		conn.setRequestMethod("POST");
		conn.setDoOutput(true);

		try (PrintWriter pw = new PrintWriter(conn.getOutputStream())) {
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(this);
			pw.println(json);
		}
		return conn.getResponseCode();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRoom() {
		return room;
	}

}
