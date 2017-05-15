package net.ukr.grygorenko_d;

import javax.persistence.*;

@Entity(name = "Rates")
public class Rate {
    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "pair_id",nullable = false)
    private String name;
    @Column(name = "rate",nullable = false)
    private double rate;

    public Rate(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public Rate() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                '}';
    }
}
