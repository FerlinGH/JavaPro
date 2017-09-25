package net.ukr.grygorenko_d.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "client_detail")
public class ClientDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "address")
	private String address;

	@Column(name = "card_number")
	private String cardNumber;

	@OneToOne(mappedBy = "clientDetail", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private Client client;

	public ClientDetail() {
		super();
	}

	public ClientDetail(int id, String address, String cardNumber, Client client) {
		super();
		this.address = address;
		this.cardNumber = cardNumber;
		this.client = client;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "ClientDetail [id=" + id + ", address=" + address + ", cardNumber=" + cardNumber + ", client=" + client
				+ "]";
	}

}
