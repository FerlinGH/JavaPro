package net.ukr.grygorenko_d;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuController {

    public static void addDish(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter dish name:");
        String name = scan.nextLine();
        System.out.println("Enter dish weight:");
        double weight = scan.nextDouble();
        System.out.println("Enter dish price:");
        double price = scan.nextDouble();
        System.out.println("With discount? (1 - Yes)");
        int choice = scan.nextInt();
        boolean isDiscount = false;
        if(choice == 1) {
            isDiscount = true;
        }

        try{
        	em.getTransaction().begin();
            Dish dish = new Dish(name,weight,price,isDiscount);
            em.persist(dish);
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
    }

    public static void selectByPrices(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter low limit of a range:");
        double low = scan.nextDouble();
        System.out.println("Enter upper limit of a range:");
        double high = scan.nextDouble();
        try{
            Query q = em.createQuery("SELECT d from Dish d WHERE d.price BETWEEN (:low) AND (:high)", Dish.class);
            q.setParameter("low", low);
            q.setParameter("high", high);
            List<Dish> list = (List<Dish>)q.getResultList();
            System.out.println("Here's your results:");
            for(Dish d:list){
                System.out.println(d);
            }
        }catch(NoResultException e) {
            System.out.println("No results matching your criteria.");
        }catch(Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
    }

    public static void selectDiscounts(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        try{
            Query q = em.createQuery("SELECT d from Dish d WHERE (d.discount = 1)", Dish.class);
            List<Dish> list = (List<Dish>)q.getResultList();
            System.out.println("Here's your results:");
            for(Dish d:list){
                System.out.println(d);
            }
        }catch(Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
    }

    public static void selectByWeight(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("SELECT d from Dish d ", Dish.class);
            List<Dish> globalList = (List<Dish>) q.getResultList();
            List<Dish> result = new ArrayList<>();
            double weight = 0;
            for (Dish d : globalList) {
                if ((weight += d.getWeight()) <= 1000) {
                    result.add(d);
                }
            }
            System.out.println("Here's your result:");
            for (Dish d : result) {
                System.out.println(d);
            }
        }catch(NoResultException e){
            System.out.println("No results! Something is wrong!");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }
    }

}
