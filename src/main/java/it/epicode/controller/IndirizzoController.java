package it.epicode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.model.Indirizzo;
import it.epicode.service.IndirizzoService;

@RestController
@RequestMapping ("/controllerindirizzo")
public class IndirizzoController {
	
	@Autowired
	IndirizzoService indirizzoService;
	
	@PostMapping ("/insertpost")
	public String insertIndirizzo (@RequestBody Indirizzo indirizzo) {
		return indirizzoService.insertIndirizzo(indirizzo);
	}
	
	@PostMapping ("/updatepost")
	public String updateIndirizzo (@RequestBody Indirizzo indirizzo ) {
		return indirizzoService.updateIndirizzo(indirizzo );
	}
	
	@GetMapping ("/removepost")
	public String removeIndirizzo (@RequestParam Long id ) {
		return indirizzoService.removeIndirizzo(id);
	}
	
	@GetMapping ("/insert")
	public String insertIndirizzo (@RequestParam String via,@RequestParam String civico, 
			@RequestParam String cap,@RequestParam  String località,@RequestParam  String comune) {
		
		return indirizzoService.insertIndirizzo(via, civico, cap, località, comune);
	}
	
	@GetMapping ("/findbyviabycivicobycap")
	public Indirizzo findByViaByCivioByCap (@RequestParam String via,@RequestParam String civico,@RequestParam  String cap) {
		return indirizzoService.findByViaByCivicoByCap(via, civico, cap);
	}
}
