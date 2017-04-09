/*
Написать код, который сериализирует и десериализирует в/из файла все значения полей класса, которые
отмечены аннотацией @Save.
 */
package net.ukr.grygorenko_d;

import java.io.File;

public class Main {
    public static void main(String[] args){
        SerializableClass sc = new SerializableClass('r',true,253,234654L,2.45F,807.7);
        SerializationActions sa = new SerializationActions(sc);


        File backup = sa.serialization();

        SerializableClass restored = sa.deserialize(backup);
        System.out.println(restored);
    }
}
