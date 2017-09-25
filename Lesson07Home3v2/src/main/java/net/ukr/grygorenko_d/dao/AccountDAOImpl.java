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
public class AccountDAOImpl implements AccountDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Account> getAccounts(int clientId) {
		Client client = entityManager.find(Client.class, clientId);
		List<Account> accounts = client.getAccounts();
		return accounts;
	}

	@Override
	public void saveClientsAccount(Client client, Account account) {
		client.addAccount(account);
		entityManager.merge(client);

	}

	@Override
	public Account getClientAccount(int clientId, int accountId) {
		Client client = entityManager.find(Client.class, clientId);
		Query query = entityManager.createQuery("SELECT a FROM Account a WHERE (a.client = :client) AND (a.id = :id )",
				Account.class);
		query.setParameter("client", client);
		query.setParameter("id", accountId);
		Account account = (Account) query.getSingleResult();
		return account;
	}

	@Override
	public void updateAccount(Account account) {
		entityManager.merge(account);

	}

	@Override
	public Account getClientAccountByCurrency(int id, Currencies currency) {
		Client client = entityManager.find(Client.class, id);
		for (Account acc : client.getAccounts()) {
			if (currency == acc.getCurrency()) {
				return acc;
			}
		}
		return null;

	}

}
