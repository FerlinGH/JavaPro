package net.ukr.grygorenko_d.yahoo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class RateExtractor {
	public static double getRate(String pair) {

		HttpURLConnection con = getConnection(pair);
		File xml = readToXML(con);
		double rate = 0;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Query.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Query parsed = (Query) unmarshaller.unmarshal(xml);

			for (Rate rt : parsed.getResults().get()) {
				System.out.println(rt.getName() + ": " + rt.getRate());
				rate = rt.getRate();
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		} finally {
			con.disconnect();
			xml.delete();
		}
		return rate;
	}

	private static HttpURLConnection getConnection(String pair) {
		String request = "http://query.yahooapis.com/v1/public/yql?format=xml&q=select*%20from%20yahoo.finance."
				+ "xchange%20where%20pair%20in%20(\"" + pair + "\")&env=store://datatables.org/alltableswithkeys";
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

	private static File readToXML(HttpURLConnection con) {
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
