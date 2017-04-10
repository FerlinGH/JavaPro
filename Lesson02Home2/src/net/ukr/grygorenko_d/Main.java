/*
Распарсить следующую структуру данных.
 */
package net.ukr.grygorenko_d;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        File info = new File("Human.txt");
        String read = readFile(info);
        Gson gson = new Gson();
        Object person = gson.fromJson(read, Object.class);
        System.out.println(person);
    }


    public static String readFile(File file) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String str = "";
            while ((str = br.readLine()) != null) {
                sb.append(str).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
