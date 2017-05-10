package net.ukr.grygorenko_d;

import javax.persistence.*;

@Entity
@Table(name = "menu")
public class Dish {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "price",nullable = false)
    private double price;

    @Column(name = "discount")
    private boolean discount;

    public Dish(String name, double weight, double price, boolean discount) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.discount = discount;
    }

    public Dish() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
