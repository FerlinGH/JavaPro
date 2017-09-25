package net.ukr.grygorenko_d.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.ukr.grygorenko_d.entity.Account;
import net.ukr.grygorenko_d.entity.Client;
import net.ukr.grygorenko_d.entity.Currencies;
import net.ukr.grygorenko_d.service.AccountService;
import net.ukr.grygorenko_d.service.ClientService;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private ClientService clientService;

	@GetMapping("/list")
	public String showAccounts(@RequestParam("clientId") int clientId, Model model) {
		List<Account> accounts = accountService.getAccounts(clientId);
		Client client = clientService.getClient(clientId);
		model.addAttribute("accounts", accounts);
		model.addAttribute("client", client);
		return "list-accounts";
	}

	@GetMapping("/new")
	public String createAccount(@RequestParam("clientId") int clientId, Model model) {
		List<Currencies> currencyOptions = accountService.getCurrencyOptions(clientId);
		System.out.println("Setting clientId: " + clientId);
		model.addAttribute("clientId", clientId);
		model.addAttribute("currencyOptions", currencyOptions);

		Account account = new Account();
		model.addAttribute("account", account);
		return "account-form";
	}

	@PostMapping("/add")
	public String addAccount(@RequestParam("clientId") int clientId, @ModelAttribute("account") Account account) {
		accountService.saveClientsAccount(clientId, account);
		return "redirect:/client/list";
	}

	@PostMapping("/fund")
	public String fundAccount(@RequestParam("clientId") int clientId, @RequestParam("accountId") int accountId,
			@RequestParam("fundAmount") double fundAmount) {
		accountService.fundAccount(clientId, accountId, fundAmount);
		return "redirect:/client/list";
	}

	@GetMapping("/evaluate")
	public String evaluateCheckout(@RequestParam("clientId") int clientId, Model model) {
		List<Account> accounts = accountService.getAccounts(clientId);
		Client client = clientService.getClient(clientId);
		Double evaluatedAmount = accountService.evaluateAmount(accounts);
		String evaluation = "NOTE: In case of checkout, you would receive " + evaluatedAmount + " UAH";
		model.addAttribute("accounts", accounts);
		model.addAttribute("client", client);
		model.addAttribute("evaluationMessage", evaluation);
		return "list-accounts";
	}

}
