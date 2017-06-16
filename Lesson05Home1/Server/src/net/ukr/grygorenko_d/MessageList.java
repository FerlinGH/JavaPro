package net.ukr.grygorenko_d;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageList {

	private final Gson gson;
	private final List<Message> list = new ArrayList<Message>();

	public MessageList() {
		gson = new GsonBuilder().create();
	}

	public synchronized void add(Message m) {
		list.add(m);
	}

	public synchronized String toJSON(int n) {
		if (n == list.size())
			return null;
		return gson.toJson(new JsonMessages(list, n));
	}
}
