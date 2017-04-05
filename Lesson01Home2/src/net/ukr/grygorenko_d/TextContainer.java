package net.ukr.grygorenko_d;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@SaveTo(path = "saved.txt")
public class TextContainer {
	private String text;

	public TextContainer(String text) {
		super();
		this.text = text;
	}

	public TextContainer() {
		super();
	}

	@Save
	public void save(File file) {
		try (PrintWriter pw = new PrintWriter(file)) {
			pw.println(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
