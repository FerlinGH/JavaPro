package net.ukr.grygorenko_d.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import net.ukr.grygorenko_d.entity.Currencies;
import net.ukr.grygorenko_d.entity.Transaction;

@Repository
public class TransactionDAOImpl implements TransactionDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Currencies> listCurrencies() {
		ArrayList<Currencies> currencies = new ArrayList<>();
		for (Currencies currency : Currencies.values()) {
			currencies.add(currency);
		}
		return currencies;
	}

	@Override
	public void saveTransaction(Transaction transaction) {
		entityManager.persist(transaction);

	}

}
