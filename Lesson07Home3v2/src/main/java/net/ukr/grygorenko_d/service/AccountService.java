package net.ukr.grygorenko_d.service;

import java.util.List;

import net.ukr.grygorenko_d.entity.Account;
import net.ukr.grygorenko_d.entity.Currencies;

public interface AccountService {

	public List<Account> getAccounts(int clientId);

	public void saveClientsAccount(int clientId, Account account);

	public List<Currencies> getCurrencyOptions(int clientId);

	public void fundAccount(int clientId, int accountId, double fundAmount);

	public List<Currencies> getAvailableCurrencies(int clientId);

	public Double evaluateAmount(List<Account> accounts);

}
