package net.ukr.grygorenko_d.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.ukr.grygorenko_d.entity.ClientDetail;
import net.ukr.grygorenko_d.service.ClientDetailService;

@Controller
@RequestMapping("/detail")
public class ClientDetailController {
	@Autowired
	private ClientDetailService clientDetailService;

	@GetMapping("/add")
	public String addDetail(@RequestParam("clientId") int clientId, Model model) {
		ClientDetail clientDetail = new ClientDetail();
		model.addAttribute("clientId", clientId);
		model.addAttribute("detail", clientDetail);
		return "detail-form";
	}

	@GetMapping("/update")
	public String updateDetail(@RequestParam("clientId") int clientId, Model model) {
		ClientDetail clientDetail = clientDetailService.getClientDetailByClientId(clientId);
		clientId = 0;
		model.addAttribute("clientId", clientId);
		model.addAttribute("detail", clientDetail);
		return "detail-form";
	}

	@GetMapping("/remove")
	public String removeDetail(@RequestParam("clientId") int clientId, Model model) {
		clientDetailService.removeDetailByClientId(clientId);
		return "redirect:/client/list";

	}

	@PostMapping("/saveDetail")
	public String saveDetail(@RequestParam("clientId") int clientId,
			@ModelAttribute("detail") ClientDetail clientDetail) {
		clientDetailService.saveDetailByClientId(clientId, clientDetail);
		return "redirect:/client/list";

	}

}
