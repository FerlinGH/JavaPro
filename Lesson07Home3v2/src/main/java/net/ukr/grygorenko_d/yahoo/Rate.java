package net.ukr.grygorenko_d.yahoo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rate")
public class Rate {
	private String name;
	private double rate;
	private String date;
	private String time;
	private double ask;
	private double bid;

	public Rate(String name, double rate, String date, String time, double ask, double bid) {
		super();
		this.name = name;
		this.rate = rate;
		this.date = date;
		this.time = time;
		this.ask = ask;
		this.bid = bid;
	}

	public Rate() {
		super();
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@XmlElement
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@XmlElement
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@XmlElement
	public double getAsk() {
		return ask;
	}

	public void setAsk(double ask) {
		this.ask = ask;
	}

	@XmlElement
	public double getBid() {
		return bid;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}

	@Override
	public String toString() {
		return "Rate [name=" + name + ", rate=" + rate + ", date=" + date + ", time=" + time + ", ask=" + ask + ", bid="
				+ bid + "]";
	}

}
