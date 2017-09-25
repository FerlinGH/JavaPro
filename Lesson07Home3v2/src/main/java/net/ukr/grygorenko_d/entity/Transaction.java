package net.ukr.grygorenko_d.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "from_client")
	private int senderId;

	@Column(name = "to_client")
	private int recipientId;

	@Column(name = "senderCurrency")
	private Currencies senderCurrency;

	@Column(name = "recipientCurrency")
	private Currencies recipientCurrency;

	@Column(name = "amount")
	private double amount;

	@Column(name = "rate")
	private double rate;

	@Column(name = "total")
	private double total;

	public Transaction(int senderId, int recipientId, Currencies senderCurrency, Currencies recipientCurrency,
			double amount, double rate, double total) {
		super();
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.senderCurrency = senderCurrency;
		this.recipientCurrency = recipientCurrency;
		this.amount = amount;
		this.rate = rate;
		this.total = total;
	}

	public Transaction() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}

	public Currencies getSenderCurrency() {
		return senderCurrency;
	}

	public void setSenderCurrency(Currencies senderCurrency) {
		this.senderCurrency = senderCurrency;
	}

	public Currencies getRecipientCurrency() {
		return recipientCurrency;
	}

	public void setRecipientCurrency(Currencies recipientCurrency) {
		this.recipientCurrency = recipientCurrency;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", senderId=" + senderId + ", recipientId=" + recipientId + ", senderCurrency="
				+ senderCurrency + ", recipientCurrency=" + recipientCurrency + ", amount=" + amount + ", total="
				+ total + "]";
	}

}
