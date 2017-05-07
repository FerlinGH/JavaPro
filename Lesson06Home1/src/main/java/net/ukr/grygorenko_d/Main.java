package net.ukr.grygorenko_d;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MyApartments myAp = new MyApartments();
        try (Connection conn = myAp.initDB()) {
            myAp.fillDB(conn);

            Scanner scan = new Scanner(System.in);
            boolean turn = true;
            while (turn) {
                System.out.println("1: choose by district");
                System.out.println("2: choose by area");
                System.out.println("3: choose by number of rooms");
                System.out.println("4: choose by price");
                System.out.println("5: exit");
                System.out.print("-> ");
                String input = scan.nextLine();
                switch (input) {
                    case "1":
                        myAp.byDistrict(scan, conn);
                        scan.nextLine();
                        break;
                    case "2":
                        myAp.byArea(scan, conn);
                        scan.nextLine();
                        break;
                    case "3":
                        myAp.byRooms(scan, conn);
                        scan.nextLine();
                        break;
                    case "4":
                        myAp.byPrice(scan, conn);
                        scan.nextLine();
                        break;
                    case "5":
                    default:
                        turn = false;
                        scan.close();
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}