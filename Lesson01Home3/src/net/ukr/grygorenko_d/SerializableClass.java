package net.ukr.grygorenko_d;

public class SerializableClass {
    @SerializableField
    private char ch;
    @SerializableField
    private boolean bool;
    @SerializableField
    private int i;
    @SerializableField
    private long lg;
    @SerializableField
    private float fl;
    @SerializableField
    private double d;

    public SerializableClass(char ch, boolean bool, int i, long lg, float fl, double d) {
        super();
        this.ch = ch;
        this.bool = bool;
        this.i = i;
        this.lg = lg;
        this.fl = fl;
        this.d = d;
    }

    public SerializableClass() {
        super();
    }

    @Override
    public String toString() {
        return "SerializableClass{" +
                "ch=" + ch +
                ", bool=" + bool +
                ", i=" + i +
                ", lg=" + lg +
                ", fl=" + fl +
                ", d=" + d +
                '}';
    }
}
