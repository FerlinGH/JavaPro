package net.ukr.grygorenko_d;

import javax.persistence.*;

@Entity(name = "Accounts")
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "currency", nullable = false)
    private Currencies currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "amount", nullable = true)
    private double amount;

    public Account(Currencies currency, Client client, double amount) {
        this.currency = currency;
        this.client = client;
        this.amount = amount;
    }

    public Account() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Currencies getCurrency() {
        return currency;
    }

    public void setName(Currencies currency) {
        this.currency = currency;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", currency='" + currency + '\'' +
                ", client=" + client +
                ", amount=" + amount +
                '}';
    }
}
