package net.ukr.grygorenko_d;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "train")
public class Train {
    private int id;
    private String from;
    private String to;
    private String date;
    private String departure;

    public Train(int id, String from, String to, String date, String departure) {
        super();
        this.id = id;
        this.from = from;
        this.to = to;
        this.date = date;
        this.departure = departure;
    }

    public Train() {
        super();
    }

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @XmlElement
    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    @XmlElement
    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    @XmlElement
    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @XmlElement
    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDeparture() {
        return departure;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date=" + date +
                ", departure='" + departure + '\'' +
                '}';
    }
}
