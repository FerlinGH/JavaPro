package net.ukr.grygorenko_d.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.ukr.grygorenko_d.entity.Currencies;
import net.ukr.grygorenko_d.entity.Transaction;
import net.ukr.grygorenko_d.service.AccountService;
import net.ukr.grygorenko_d.service.ClientService;
import net.ukr.grygorenko_d.service.TransactionService;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private ClientService clientService;

	@GetMapping("/create")
	public String createTransaction(@RequestParam("clientId") int clientId, Model model) {
		Transaction transaction = new Transaction();
		transaction.setSenderId(clientId);
		List<Currencies> availableCurrencies = accountService.getAvailableCurrencies(clientId);
		List<Currencies> currencies = transactionService.listCurrencies();
		model.addAttribute("availableCurrencies", availableCurrencies);
		model.addAttribute("transaction", transaction);
		model.addAttribute("currencies", currencies);
		return "transaction-form";
	}

	@PostMapping("/validation")
	public String validateTransaction(@ModelAttribute("transaction") Transaction transaction, Model model) {

		Map<Boolean, String> validationStatus = transactionService.validateTransaction(transaction);
		if (validationStatus.containsKey(true)) {
			System.out.println(validationStatus.get(true));
			model.addAttribute("transaction", transaction);
			model.addAttribute("sender", clientService.getClient(transaction.getSenderId()));
			model.addAttribute("recipient", clientService.getClient(transaction.getRecipientId()));
			return "transaction-confirmation";
		} else {
			System.out.println(validationStatus.get(false));
			model.addAttribute("message", validationStatus.get(false));
			return "transaction-form";
		}
	}

	@PostMapping("/perform")
	public String performTransaction(@ModelAttribute("transaction") Transaction transaction, Model model) {
		Map<Boolean, String> validationStatus = transactionService.validateTransaction(transaction);
		if (validationStatus.containsKey(false)) {
			model.addAttribute("message", validationStatus.get(false));
			return "transaction-form";
		} else {
			transactionService.performTransaction(transaction);
			transactionService.saveTransaction(transaction);
			return "redirect:/client/list";
		}

	}

}
