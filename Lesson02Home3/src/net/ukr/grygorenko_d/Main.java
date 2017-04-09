/*
Написать парсер для Yahoo Finance в
режиме XML (format=xml).
 */
package net.ukr.grygorenko_d;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {

        HttpURLConnection con = getConnection();

        File xml = readToXML(con);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Query.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Query parsed = (Query) unmarshaller.unmarshal(xml);

            for (Rate rt : parsed.getResults().get()) {
                System.out.println(rt.getName().toUpperCase() + " : " + rt.getRate());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public static HttpURLConnection getConnection() {
        String request = "http://query.yahooapis.com/v1/public/yql?format=xml&q=select*%20from%20yahoo.finance." +
                "xchange%20where%20pair%20in%20(\"USDEUR\",\"USDUAH\")&env=store://datatables.org/alltableswithkeys";
        URL url = null;
        try {
            url = new URL(request);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static File readToXML(HttpURLConnection con) {
        File xml = new File("result.xml");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
             PrintWriter pw = new PrintWriter(xml)) {
            StringBuilder sb = new StringBuilder();
            String str = "";
            while ((str = br.readLine()) != null) {
                sb.append(str).append(System.lineSeparator());
            }
            pw.write(sb.toString().toLowerCase());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }

}
