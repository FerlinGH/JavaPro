package net.ukr.grygorenko_d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonMessages {
	private final List<Message> list = new ArrayList<>();

	public JsonMessages() {
		super();
	}

	public List<Message> getList() {
		return Collections.unmodifiableList(list);
	}
}
