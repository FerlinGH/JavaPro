package net.ukr.grygorenko_d;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class BankController {

    public BankController() {
    }

    public void initDB(EntityManagerFactory emf) {
        getRatesFromYahoo(emf);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Client client1 = new Client("Vasya", new ArrayList<>());
            Client client2 = new Client("Kolya", new ArrayList<>());
            Account account1 = new Account(Currencies.USD, client1, 100);
            Account account2 = new Account(Currencies.EUR, client1, 200);
            client1.addAccount(account1);
            client1.addAccount(account2);
            Account account3 = new Account(Currencies.UAH, client2, 1000);
            client2.addAccount(account3);
            em.persist(client1);
            em.persist(client2);
            em.persist(account1);
            em.persist(account2);
            em.persist(account3);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    private void getRatesFromYahoo(EntityManagerFactory emf) {
        StringBuilder sb = new StringBuilder();
        String input = "";
        try {
            Process p = Runtime.getRuntime().exec("java -jar  \"src\\main\\resources\\YahooFinance-1.0-SNAPSHOT.jar\"");
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((input = br.readLine()) != null) {
                sb.append(input).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            for (String line : sb.toString().split(System.lineSeparator())) {
                String[] params = line.split(" : ");
                Rate r = new Rate(params[0], Double.parseDouble(params[1]));
                em.persist(r);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void fundAccount(EntityManagerFactory emf) throws NoResultException, NonUniqueResultException {
        EntityManager em = emf.createEntityManager();
        Client client = selectClient(em);
        if (client == null) return;
        Currencies currency = selectCurrencyAccount();
        if (currency == null) return;
        Account account = getClientsAccount(client, currency, true);
        if (account == null) return;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter amount:");
        double am = scan.nextDouble();
        em.getTransaction().begin();
        try {
            account.setAmount(account.getAmount() + am);
            em.persist(client);
            em.persist(account);
            Transaction tr = new Transaction(client.getId(), client.getId(), currency, currency, am, am);
            em.persist(tr);
            em.getTransaction().commit();
            System.out.println("Successful transaction.");
        } catch (Exception e) {
            System.out.println("Error in transaction.");
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void fundAnotherClient(EntityManagerFactory emf) throws NoResultException, NonUniqueResultException {
        EntityManager em = emf.createEntityManager();
        System.out.println("Selecting sender client.");
        Client sender = selectClient(em);
        if (sender == null) return;
        System.out.println("Now selecting recipient client.");
        Client recipient = selectClient(em);
        if (recipient == null) return;
        System.out.println("Please select sender's currency.");
        Currencies fromCurrency = selectCurrencyAccount();
        if (fromCurrency == null) return;
        Account senderAccount = getClientsAccount(sender, fromCurrency, false);
        if (senderAccount == null) return;
        System.out.println("Now please select recipient's currency.");
        Currencies toCurrency = selectCurrencyAccount();
        if (toCurrency == null) return;
        Account recipientAccount = getClientsAccount(recipient, toCurrency, false);
        if (recipientAccount == null) return;
        transferMoney(em, sender, recipient, senderAccount, recipientAccount, fromCurrency, toCurrency);
        em.close();
    }

    public void convertCurrencies(EntityManagerFactory emf) throws NoResultException, NonUniqueResultException {
        EntityManager em = emf.createEntityManager();
        Scanner scan = new Scanner(System.in);
        Client client = selectClient(em);
        if (client == null) return;
        System.out.println("From which currency?");
        Currencies fromCurrency = selectCurrencyAccount();
        if (fromCurrency == null) return;
        Account fromAccount = getClientsAccount(client, fromCurrency, false);
        if (fromAccount == null) return;
        System.out.println("Now please select desired currency.");
        Currencies toCurrency = selectCurrencyAccount();
        if (toCurrency == null) return;
        Account toAccount = getClientsAccount(client, toCurrency, true);
        if (toAccount == null) return;
        transferMoney(em, client, client, fromAccount, toAccount, fromCurrency, toCurrency);
        em.close();
    }

    public void checkOut(EntityManagerFactory emf) throws NoResultException, NonUniqueResultException {
        EntityManager em = emf.createEntityManager();
        Scanner scan = new Scanner(System.in);
        Client client = selectClient(em);
        if (client == null) return;
        System.out.println("Current active accounts:");
        for (Account a : client.getAccounts()) {
            System.out.println(a);
        }
        Currencies toCurrency = Currencies.UAH;
        double totalSum = 0;
        double rate = 0;
        for (Account account : client.getAccounts()) {
            Currencies fromCurrency = account.getCurrency();
            rate = (fromCurrency.equals(toCurrency)) ? 1 : getPairRate(em, fromCurrency, toCurrency);
            totalSum += account.getAmount() * rate;
        }
        System.out.printf("In case of a check-out, this client would receive %.2f UAH.", totalSum);
        System.out.println();
        em.close();
    }

    private void transferMoney(EntityManager em, Client sender, Client recipient, Account senderAccount,
                               Account recipientAccount, Currencies fromCurrency, Currencies toCurrency) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter amount:");
        double am = scan.nextDouble();
        em.getTransaction().begin();
        try {
            if (senderAccount.getAmount() < am) {
                throw new Exception("You do not have enough money, aborting.");
            }
            senderAccount.setAmount(senderAccount.getAmount() - am);
            double rate = (fromCurrency.equals(toCurrency)) ? 1 : getPairRate(em, fromCurrency, toCurrency);
            recipientAccount.setAmount(recipientAccount.getAmount() + (am * rate));
            em.persist(sender);
            em.persist(recipient);
            em.persist(senderAccount);
            em.persist(recipientAccount);
            Transaction tr = new Transaction(sender.getId(), recipient.getId(), fromCurrency, toCurrency, am, am * rate);
            em.persist(tr);
            em.getTransaction().commit();
            System.out.println("Successful transaction.");
        } catch (Exception e) {
            System.out.println("Error in transaction.");
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    private double getPairRate(EntityManager em, Currencies senderCurrency, Currencies recipientCurrency) {
        double rate = 0;
        String pair = String.valueOf(senderCurrency) + "/" + String.valueOf(recipientCurrency);
        Query q = em.createQuery("SELECT r FROM Rates r WHERE (r.name = :pair)", Rate.class);
        q.setParameter("pair", pair);
        Rate r = (Rate) q.getSingleResult();
        rate = r.getRate();
        System.out.println("Rate for pair " + pair + " is " + r.getRate());
        return rate;
    }

    private Account getClientsAccount(Client client, Currencies currency, boolean isCreatable) {
        Account account = null;
        for (Account a : client.getAccounts()) {
            if (a.getCurrency().equals(currency)) {
                account = a;
                break;
            }
        }
        if (account == null) {
            if (isCreatable) {
                System.out.println("This account is  not yet created, creating now.");
                account = new Account(currency, client, 0);
                client.addAccount(account);
            } else {
                System.out.println("Client does not have the account in such currency, aborting.");
            }
        }
        return account;
    }

    private Currencies selectCurrencyAccount() {
        Scanner scan = new Scanner(System.in);
        Currencies currency;
        System.out.println("Select client's account (1 - EUR, 2 - UAH, 3 - USD): ");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                currency = Currencies.EUR;
                break;
            case 2:
                currency = Currencies.UAH;
                break;
            case 3:
                currency = Currencies.USD;
                break;
            default:
                System.out.println("Cannot recognize currency.");
                return null;
        }
        return currency;
    }

    private Client selectClient(EntityManager em) throws NoResultException, NonUniqueResultException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter client's name:");
        String input = scan.nextLine();
        Query q = em.createQuery("SELECT c FROM Clients c WHERE (c.name = :name)", Client.class);
        q.setParameter("name", input);
        Client client = (Client) q.getSingleResult();
        System.out.println(client);
        System.out.print("Correct? (Y/N) :");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("n")) {
            System.out.println("Transaction aborted by user.");
            em.close();
            return null;
        } else {
            return client;
        }
    }

}
