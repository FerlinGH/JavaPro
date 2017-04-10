package net.ukr.grygorenko_d;

public class JSON {
    private Human human;

    public JSON(Human human) {
        super();
        this.human = human;
    }

    public JSON() {
        super();
    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

    @Override
    public String toString() {
        return "JSON{" +
                "human=" + human +
                '}';
    }
}
