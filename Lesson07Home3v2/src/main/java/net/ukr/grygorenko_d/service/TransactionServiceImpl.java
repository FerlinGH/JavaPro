package net.ukr.grygorenko_d.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ukr.grygorenko_d.dao.AccountDAO;
import net.ukr.grygorenko_d.dao.ClientDAO;
import net.ukr.grygorenko_d.dao.TransactionDAO;
import net.ukr.grygorenko_d.entity.Account;
import net.ukr.grygorenko_d.entity.Client;
import net.ukr.grygorenko_d.entity.Currencies;
import net.ukr.grygorenko_d.entity.Transaction;
import net.ukr.grygorenko_d.yahoo.RateExtractor;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDAO transactionDAO;

	@Autowired
	private ClientDAO clientDAO;

	@Autowired
	private AccountDAO accountDAO;

	@Override
	@Transactional
	public List<Currencies> listCurrencies() {
		List<Currencies> currencies = transactionDAO.listCurrencies();
		return currencies;
	}

	@Override
	@Transactional
	public Map<Boolean, String> validateTransaction(Transaction tempTransaction) {
		Map<Boolean, String> validationStatus = new HashMap<>();
		int clientId = tempTransaction.getSenderId();
		int recipientId = tempTransaction.getRecipientId();
		
		Client sender = clientDAO.getClientById(clientId);
		if (sender == null) {
			validationStatus.put(false, "Validation error: sender not found!");
			return validationStatus;
		}

		Client recipient = clientDAO.getClientById(recipientId);
		if (recipient == null) {
			validationStatus.put(false, "Validation error: recipient not found!");
			return validationStatus;
		}

		Currencies senderCurrency = tempTransaction.getSenderCurrency();
		if (!clientDAO.findAccount(clientId, senderCurrency)) {
			validationStatus.put(false, "Validation error: sender's account not found!");
			return validationStatus;
		}

		Currencies recipientCurrency = tempTransaction.getRecipientCurrency();
		if (!clientDAO.findAccount(recipientId, recipientCurrency)) {
			validationStatus.put(false, "Validation error: recipient's account not found!");
			return validationStatus;
		}

		Account senderAccount = accountDAO.getClientAccountByCurrency(clientId, tempTransaction.getSenderCurrency());
		if (senderAccount == null) {
			validationStatus.put(false, "Unexpected error: sender's account is null!");
			return validationStatus;
		}
		
		double amount = tempTransaction.getAmount();
		if (senderAccount.getAmount() < amount) {
			validationStatus.put(false, "Validation error: sender's account not sufficient!");
			return validationStatus;
		}

		if (tempTransaction.getSenderCurrency() == tempTransaction.getRecipientCurrency()) {
			tempTransaction.setRate(1);
		} else {
			String pair = tempTransaction.getSenderCurrency().name() + tempTransaction.getRecipientCurrency().name();
			tempTransaction.setRate(RateExtractor.getRate(pair));
		}

		double total = tempTransaction.getAmount() * tempTransaction.getRate();
		total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
		tempTransaction.setTotal(total);

		validationStatus.put(true, "Validate transaction: transaction is possible!");
		return validationStatus;
	}

	@Override
	@Transactional
	public void performTransaction(Transaction tempTransaction) {
		Account senderAccount = accountDAO.getClientAccountByCurrency(tempTransaction.getSenderId(),
				tempTransaction.getSenderCurrency());
		senderAccount.setAmount(senderAccount.getAmount() - tempTransaction.getAmount());

		Account recipientAccount = accountDAO.getClientAccountByCurrency(tempTransaction.getRecipientId(),
				tempTransaction.getRecipientCurrency());
		recipientAccount.setAmount(recipientAccount.getAmount() + tempTransaction.getTotal());

		Client sender = clientDAO.getClientById(tempTransaction.getSenderId());
		Client recipient = clientDAO.getClientById(tempTransaction.getRecipientId());
		accountDAO.saveClientsAccount(sender, senderAccount);
		accountDAO.saveClientsAccount(recipient, recipientAccount);
	}

	@Override
	@Transactional
	public void saveTransaction(Transaction transaction) {
		transactionDAO.saveTransaction(transaction);

	}

}
