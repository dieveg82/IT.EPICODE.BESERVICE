package it.epicode.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.model.Fattura;
import it.epicode.service.FatturaService;

@RestController
@RequestMapping ("/controllerfattura")
public class FatturaController {

	@Autowired
	FatturaService fatturaService;
	
	@PostMapping ("/insertfatturapost")
	public String insertFattura (@RequestBody Fattura fattura){
		return fatturaService.insertFattura(fattura);
	}
	
	@PostMapping ("/updatefatturapost")
	public String updateFattura (@RequestBody Fattura fattura , Long id) {
		return fatturaService.updateFattura(fattura , id);
	}
	
	@PostMapping ("/removefattura")
	public String removeFattura (@RequestBody Fattura fattura) {
		return fatturaService.removeFattura(fattura);
	}
	
	@GetMapping ("/insertfattura")
	public String insertFattura (@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data, int numeroFattura, int anno, int importo, String statoFattura,
			String partitaIva) {
		return fatturaService.insertFattura(data, numeroFattura, anno, importo, statoFattura, partitaIva);
	}
	
	
	@GetMapping ("/findbystato")
	public List<Fattura> findByStato (@RequestParam String stato) {
		return fatturaService.findByStato(stato);
	}
	
	@GetMapping ("/findbynumerofattura")
	public List<Fattura> findByNumeroFattura (@RequestParam  int numeroFattura) {
		return fatturaService.findByNumeroFattura(numeroFattura);
	}
	
	@GetMapping ("/findbycliente")
	public List<Fattura> findByCliente (@RequestParam  String ragioneSociale){
		return fatturaService.findByCliente(ragioneSociale);
	}
	
	@GetMapping ("/findbydata")
	public List<Fattura> findByData (@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate data){
		return fatturaService.findByData(data);
	}
	
	@GetMapping ("/findbyanno")
	public List<Fattura> findByAnno (@RequestParam int anno){
		return fatturaService.findByAnno(anno);
	}
	
	
}
