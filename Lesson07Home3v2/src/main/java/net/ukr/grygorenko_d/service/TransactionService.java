package net.ukr.grygorenko_d.service;

import java.util.List;
import java.util.Map;

import net.ukr.grygorenko_d.entity.Currencies;
import net.ukr.grygorenko_d.entity.Transaction;

public interface TransactionService {

	public List<Currencies> listCurrencies();

	public Map<Boolean, String> validateTransaction(Transaction tempTransaction);

	public void performTransaction(Transaction tempTransaction);

	public void saveTransaction(Transaction transaction);

}
