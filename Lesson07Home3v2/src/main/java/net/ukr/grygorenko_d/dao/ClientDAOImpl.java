package net.ukr.grygorenko_d.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import net.ukr.grygorenko_d.entity.Account;
import net.ukr.grygorenko_d.entity.Client;
import net.ukr.grygorenko_d.entity.Currencies;

@Repository
public class ClientDAOImpl implements ClientDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveClient(Client client) {
		entityManager.merge(client);
	}

	@Override
	public List<Client> getClients() {
		Query query = entityManager.createQuery("SELECT c from Client c ORDER BY lastName", Client.class);
		List<Client> clients = query.getResultList();

		return clients;
	}

	@Override
	public Client getClientById(int clientId) {
		Client tempClient = entityManager.find(Client.class, clientId);
		return tempClient;
	}

	@Override
	public void deleteClient(Client tempClient) {
		entityManager.remove(tempClient);

	}

	@Override
	public boolean findAccount(int id, Currencies currency) {
		Client client = entityManager.find(Client.class, id);
		List<Account> clientAccounts = client.getAccounts();
		boolean contains = false;
		for (Account account : clientAccounts) {
			if (currency == account.getCurrency()) {
				contains = true;
			}
		}
		return contains;
	}

}
