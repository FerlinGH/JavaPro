package net.ukr.grygorenko_d.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.ukr.grygorenko_d.entity.Account;
import net.ukr.grygorenko_d.entity.Client;
import net.ukr.grygorenko_d.entity.Currencies;
import net.ukr.grygorenko_d.service.ClientService;

@Component
public class InitializeDatabase {
	@Autowired
	private ClientService clientService;

	@PostConstruct
	public void fillDatabase() {
		Client client1 = new Client("Peter", "First", "peter@springbank.com");
		Client client2 = new Client("Michael", "Second", "michael@springbank.com");
		Client client3 = new Client("Vasyl", "Third", "vasyl@springbank.com");

		Account account1 = new Account(Currencies.USD, 100);
		client1.addAccount(account1);
		// Account account2 = new Account(Currencies.EUR, 0);
		// client2.addAccount(account2);
		Account account3 = new Account(Currencies.USD, 10);
		Account account4 = new Account(Currencies.UAH, 0);
		client3.addAccount(account3);
		client3.addAccount(account4);

		clientService.saveClient(client1);
		clientService.saveClient(client2);
		clientService.saveClient(client3);

	}
}
