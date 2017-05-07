package net.ukr.grygorenko_d;

import java.sql.*;
import java.util.Scanner;

public class MyApartments {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/myapartments";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "AdminPassword";


    public Connection initDB() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        try(Statement st = conn.createStatement()){
            st.execute("DROP TABLE IF EXISTS Districts");
            st.execute("DROP TABLE IF EXISTS Apartments");
            String query = "CREATE TABLE Districts ("+
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "+
                    "name VARCHAR(20) NOT NULL);";
            st.execute(query);
            query = "CREATE TABLE Apartments("+
                        "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "+
                        "District VARCHAR(20) DEFAULT NULL, "+
                        "Address VARCHAR(30) NOT NULL, "+
                        "Area FLOAT(6,2) NOT NULL, "+
                        "Rooms TINYINT UNSIGNED NOT NULL, "+
                        "Price INT NOT NULL);";
            st.execute(query);
        }
        return conn;
    }

    public void fillDB(Connection conn) throws SQLException {
        try(Statement st = conn.createStatement()){
            st.execute("INSERT INTO Districts (name) VALUE ('Darnytsky')");
            st.execute("INSERT INTO Districts (name) VALUE ('Dniprovsky')");
            st.execute("INSERT INTO Districts (name) VALUE ('Pechersk')");
            String query = "INSERT INTO  Apartments (Address,Area,Rooms,Price) VALUES"+
                    "('167 Bazhana ave, 34','60.2','2','94000')";
            st.execute(query);
            query = "INSERT INTO  Apartments (Address,Area,Rooms,Price) VALUES"+
                    "('46 Revutskogo str, 29','85.63','3','105000')";
            st.execute(query);
            query = "INSERT INTO  Apartments (Address,Area,Rooms,Price) VALUES"+
                    "('24 Buchmy str, 5','42.4','1','63000')";
            st.execute(query);
            query = "INSERT INTO  Apartments (Address,Area,Rooms,Price) VALUES"+
                    "('12 Khreschatyk str, 20','65.87','2','174500')";
            st.execute(query);
            st.execute("UPDATE Apartments SET District = (SELECT name FROM Districts WHERE id = 1) WHERE id = 1");
            st.execute("UPDATE Apartments SET District = (SELECT name FROM Districts WHERE id = 1) WHERE id = 2");
            st.execute("UPDATE Apartments SET District = (SELECT name FROM Districts WHERE id = 2) WHERE id = 3");
            st.execute("UPDATE Apartments SET District = (SELECT name FROM Districts WHERE id = 3) WHERE id = 4");
        }
    }

    private void displayMetadata(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        for (int i = 1; i <= md.getColumnCount(); i++)
            System.out.printf("%20s", md.getColumnName(i));
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= md.getColumnCount(); i++) {
                System.out.printf("%20s", rs.getString(i));
            }
            System.out.println();
        }
        System.out.println();
    }

    public void byDistrict(Scanner scan, Connection conn) throws SQLException {
        String head = "SELECT * from Apartments WHERE District = '";
        System.out.println("Apartments are available at the folowing districts:");
        try(PreparedStatement pst = conn.prepareStatement("SELECT DISTINCT District FROM Apartments")) {
            try (ResultSet rs = pst.executeQuery()) {
                displayMetadata(rs);
            }
        }
        System.out.println("Type district name to see available apartments there.");
        String input = scan.nextLine();
        try(PreparedStatement pst = conn.prepareStatement(head + input + "'")) {
            try (ResultSet rs = pst.executeQuery()) {
                displayMetadata(rs);
            }
        }
        System.out.println();
    }

    public void byArea(Scanner scan, Connection conn) throws SQLException {
        System.out.println("Enter minimal area of apartment:");
        float low = scan.nextFloat();
        System.out.println("Enter maximal area of apartment:");
        float high = scan.nextFloat();
        String query = "SELECT * from Apartments WHERE area BETWEEN " + low + " AND " + high;
        try(PreparedStatement pst = conn.prepareStatement(query)) {
            try (ResultSet rs = pst.executeQuery()) {
                displayMetadata(rs);
            }
        }
        System.out.println();
    }

    public void byRooms(Scanner scan, Connection conn) throws SQLException {
        System.out.println("Enter minimal number of rooms:");
        float low = scan.nextByte();
        System.out.println("Enter maximum number of rooms:");
        float high = scan.nextByte();
        String query = "SELECT * from Apartments WHERE Rooms BETWEEN " + low + " AND " + high;
        try(PreparedStatement pst = conn.prepareStatement(query)) {
            try (ResultSet rs = pst.executeQuery()) {
                displayMetadata(rs);
            }
        }
        System.out.println();
    }

    public void byPrice(Scanner scan, Connection conn) throws SQLException {
        System.out.println("Enter minimal price of apartment:");
        float low = scan.nextInt();
        System.out.println("Enter maximum price of apartment:");
        float high = scan.nextInt();
        String query = "SELECT * from Apartments WHERE Price BETWEEN " + low + " AND " + high;
        try(PreparedStatement pst = conn.prepareStatement(query)) {
            try (ResultSet rs = pst.executeQuery()) {
                displayMetadata(rs);
            }
        }
        System.out.println();
    }

}


