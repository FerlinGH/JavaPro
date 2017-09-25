package net.ukr.grygorenko_d.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ukr.grygorenko_d.dao.AccountDAO;
import net.ukr.grygorenko_d.dao.ClientDAO;
import net.ukr.grygorenko_d.entity.Account;
import net.ukr.grygorenko_d.entity.Client;
import net.ukr.grygorenko_d.entity.Currencies;
import net.ukr.grygorenko_d.yahoo.RateExtractor;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDAO accountDAO;

	@Autowired
	private ClientDAO clientDAO;

	@Override
	@Transactional
	public List<Account> getAccounts(int clientId) {
		List<Account> accounts = accountDAO.getAccounts(clientId);
		return accounts;
	}

	@Override
	@Transactional
	public void saveClientsAccount(int clientId, Account account) {
		Client client = clientDAO.getClientById(clientId);
		accountDAO.saveClientsAccount(client, account);

	}

	@Override
	@Transactional
	public List<Currencies> getCurrencyOptions(int clientId) {
		ArrayList<Currencies> currencyOptions = new ArrayList<>();
		for (Currencies currency : Currencies.values()) {
			currencyOptions.add(currency);
		}

		List<Account> accounts = accountDAO.getAccounts(clientId);
		for (Account account : accounts) {
			currencyOptions.remove(account.getCurrency());
		}

		return currencyOptions;
	}

	@Override
	@Transactional
	public void fundAccount(int clientId, int accountId, double fundAmount) {
		Account account = accountDAO.getClientAccount(clientId, accountId);
		double amount = account.getAmount();
		account.setAmount(amount += fundAmount);
		accountDAO.updateAccount(account);
	}

	@Override
	public List<Currencies> getAvailableCurrencies(int clientId) {
		ArrayList<Currencies> availableCurrencies = new ArrayList<>();
		List<Account> accounts = accountDAO.getAccounts(clientId);
		for (Account account : accounts) {
			availableCurrencies.add(account.getCurrency());
		}

		return availableCurrencies;
	}

	@Override
	public Double evaluateAmount(List<Account> accounts) {
		double evaluatedAmount = 0;
		for (Account account : accounts) {
			if (account.getCurrency().name() == "UAH") {
				evaluatedAmount += account.getAmount();
			} else {
				if (account.getAmount() != 0) {
					String pair = account.getCurrency().name() + "uah";
					double amountInUAH = account.getAmount() * RateExtractor.getRate(pair);
					evaluatedAmount += amountInUAH;
				} else {
					continue;
				}
			}
		}
		evaluatedAmount = new BigDecimal(evaluatedAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
		return evaluatedAmount;
	}

}
