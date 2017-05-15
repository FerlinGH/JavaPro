package net.ukr.grygorenko_d;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA");
        BankController bk = new BankController();
        bk.initDB(emf);
        boolean turn = true;
        Scanner scan = new Scanner(System.in);
        try{
            while(turn){
                System.out.println("1: fund client's account");
                System.out.println("2: make transfer to another client");
                System.out.println("3: convert your currencies");
                System.out.println("4: estimate check-out amount");
                System.out.println("5: exit");
                System.out.print("-> ");
                int line = scan.nextInt();
                switch(line){
                    case 1:
                        bk.fundAccount(emf);
                        break;
                    case 2:
                        bk.fundAnotherClient(emf);
                        break;
                    case 3:
                        bk.convertCurrencies(emf);
                        break;
                    case 4:
                        bk.checkOut(emf);
                        break;
                    default:
                        scan.close();
                        turn = false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            emf.close();
        }
    }


    }

