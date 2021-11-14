package it.epicode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.model.StatoFattura;
import it.epicode.service.StatoFatturaService;

@RestController
@RequestMapping("/controllerstatofattura")
public class StatoFatturaController {

	@Autowired
	StatoFatturaService statoFatturaService;

	@PostMapping("/insertstatopost")
	public String insertStatoPost(@RequestBody StatoFattura stato) {
		return statoFatturaService.insertStatoPost(stato);
	}

	@PostMapping("/update")
	public String updateStato(@RequestBody StatoFattura stato) {
		return statoFatturaService.updateStato(stato);
	}

	@PostMapping("/remove")
	public String removeStato(@RequestBody StatoFattura stato) {
		return statoFatturaService.removeStato(stato);
	}

	@GetMapping("/insertstato")
	public String insertStato(@RequestParam String nome) {
		return statoFatturaService.insertStato(nome);
	}

}
