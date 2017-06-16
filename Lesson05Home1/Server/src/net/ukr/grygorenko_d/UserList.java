package net.ukr.grygorenko_d;

import java.util.ArrayList;
import java.util.List;

public class UserList {
	private static UserList userList = new UserList();
	private List<User> list = new ArrayList<>();

	private UserList() {
		super();
	}

	public static UserList getInstance() {
		return userList;
	}

	public List<User> getList() {
		return list;
	}

	public void addUser(User user) {
		list.add(user);
	}

}
