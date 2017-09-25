package net.ukr.grygorenko_d.dao;

import java.util.List;

import net.ukr.grygorenko_d.entity.Account;
import net.ukr.grygorenko_d.entity.Client;
import net.ukr.grygorenko_d.entity.Currencies;

public interface AccountDAO {

	public List<Account> getAccounts(int clientId);

	public void saveClientsAccount(Client client, Account account);

	public Account getClientAccount(int clientId, int accountId);

	public void updateAccount(Account account);

	public Account getClientAccountByCurrency(int id, Currencies currency);

}
