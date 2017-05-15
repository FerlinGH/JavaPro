package net.ukr.grygorenko_d;

import javax.persistence.*;

@Entity(name = "Transactions")
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "from_client")
    private long senderId;
    @Column(name = "to_client")
    private long recipientId;
    @Column(name = "senderCurrency")
    private Currencies senderCurrency;
    @Column(name = "recipientCurrency")
    private Currencies recipientCurrency;
    @Column(name = "amount")
    private double amount;
    @Column(name = "total")
    private double total;

    public Transaction(long senderId, long recipientId, Currencies senderCurrency, Currencies recipientCurrency, double amount, double total) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.senderCurrency = senderCurrency;
        this.recipientCurrency = recipientCurrency;
        this.amount = amount;
        this.total = total;
    }

    public Transaction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(long recipientId) {
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", recipientId=" + recipientId +
                ", senderCurrency=" + senderCurrency +
                ", recipientCurrency=" + recipientCurrency +
                ", amount=" + amount +
                ", total=" + total +
                '}';
    }
}
