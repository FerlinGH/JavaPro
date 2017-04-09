package net.ukr.grygorenko_d;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "trains")
public class Trains {

    @XmlElement(name = "train")
    private List<Train> trains = new ArrayList<>();

    public Trains(List<Train> trains) {
        super();
        this.trains = trains;
    }

    public Trains() {
        super();
    }

    public List<Train> get() {
        return trains;
    }

    public void set(List<Train> trains) {
        this.trains = trains;
    }

    public void add(Train train){
        trains.add(train);
    }

    @Override
    public String toString() {
        return Arrays.toString(trains.toArray());
    }
}
