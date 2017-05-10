package net.ukr.grygorenko_d;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA");
        boolean turn = true;
        Scanner scan = new Scanner(System.in);
        try{
            while(turn){
                System.out.println("1: add new dish to the menu");
                System.out.println("2: select dishes by price range");
                System.out.println("3: select dishes with discounts only");
                System.out.println("4: select by total weight");
                System.out.println("5: exit");
                System.out.print("-> ");
                int line = scan.nextInt();
                switch(line){
                    case 1:
                        MenuController.addDish(emf);
                        break;
                    case 2:
                        MenuController.selectByPrices(emf);
                        break;
                    case 3:
                        MenuController.selectDiscounts(emf);
                        break;
                    case 4:
                        MenuController.selectByWeight(emf);
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
