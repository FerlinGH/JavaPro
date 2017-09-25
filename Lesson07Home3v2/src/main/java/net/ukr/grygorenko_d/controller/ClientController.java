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

import net.ukr.grygorenko_d.entity.Client;
import net.ukr.grygorenko_d.service.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private ClientService clientService;

	@GetMapping("/list")
	public String showClients(Model model) {
		List<Client> clients = clientService.getClients();
		model.addAttribute("clients", clients);
		return "list-clients";

	}

	@GetMapping("/new")
	public String newClient(Model model) {
		Client newClient = new Client();
		model.addAttribute("client", newClient);
		return "client-form";
	}

	@PostMapping("/save")
	public String saveClient(@ModelAttribute("client") Client newClient) {
		clientService.saveClient(newClient);
		return "redirect:/client/list";
	}

	@GetMapping("/delete")
	public String deleteClient(@RequestParam("clientId") int clientId) {
		clientService.deleteClient(clientId);
		return "redirect:/client/list";
	}

}
