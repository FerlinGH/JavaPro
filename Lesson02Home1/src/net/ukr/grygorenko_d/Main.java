/*
Есть список поездов, представленный с виде XML. Вывести на экран информацию о тех поездах, которые
отправляются сегодня с 15:00 до 19:00. Написать код для добавления новых поездов в существующий XML.
 */
package net.ukr.grygorenko_d;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        Trains newTrains = new Trains();
        newTrains.add(new Train(1, "Kyiv", "Donetsk", "08.04.2017", "15:05"));
        newTrains.add(new Train(2, "Lviv", "Donetsk", "08.04.2017", "19:05"));
        newTrains.add(new Train(3, "Kyiv", "Odesa", "08.04.2017", "18:43"));

        try {
            File file = new File("timetable.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Trains.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(newTrains, file);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Trains recovered = (Trains) unmarshaller.unmarshal(file);
            for (Train tr : recovered.get()) {
                String departure = tr.getDeparture();
                String[] temp = departure.split(":");
                int hour = Integer.parseInt(temp[0]);
                int minute = Integer.parseInt(temp[1]);
                if ((hour >= 15 && hour < 19) && (minute >= 0 && minute <= 59)) {
                    System.out.println(tr);
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
