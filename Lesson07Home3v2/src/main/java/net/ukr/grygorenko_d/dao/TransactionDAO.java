package net.ukr.grygorenko_d.dao;

import java.util.List;

import net.ukr.grygorenko_d.entity.Currencies;
import net.ukr.grygorenko_d.entity.Transaction;

public interface TransactionDAO {

	public List<Currencies> listCurrencies();

	public void saveTransaction(Transaction transaction);

}
