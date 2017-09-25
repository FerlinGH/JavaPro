package net.ukr.grygorenko_d.yahoo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "results")
public class Results {
	@XmlElement(name = "rate")
	private List<Rate> results = new ArrayList<>();

	public Results(List<Rate> results) {
		super();
		this.results = results;
	}

	public Results() {
		super();
	}

	public void add(Rate rate) {
		results.add(rate);
	}

	public List<Rate> get() {
		return results;
	}

	@Override
	public String toString() {
		return Arrays.toString(results.toArray());
	}
}
