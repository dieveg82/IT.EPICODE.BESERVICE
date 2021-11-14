package it.epicode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.model.Comune;
import it.epicode.service.ComuneService;

@RestController
@RequestMapping ("/controllercomune")
public class ComuneController {

	@Autowired 
	ComuneService comuneService;
	
	// UTILIZZO IL RESPONSE ENTITY PER SCOPO DIDATTICO 
	
	@GetMapping("/findcomunepage")
	public ResponseEntity<Page<Comune>> getAllComune(Pageable page) {
		Page<Comune> findAll = comuneService.findAll(page);
		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping ("/insertcomunepost")
	public String insertComunePost (@RequestBody Comune comune ) {
		return comuneService.inserimentoComunePost(comune);
	}
	
	@PostMapping ("/updatecomunepost")
	public String updateComunePost (@RequestBody Comune comune ) {
		return comuneService.updateComune (comune);
	}
	
	@GetMapping ("/removecomune")
	public String removecomune (@RequestParam Long id) {
		return comuneService.removeComune(id);
	}
	
	@GetMapping ("/insertcomune")
	public String insertComune (@RequestParam String nomeComune , @RequestParam String nomeProvincia) {
		return comuneService.inserimentoComune(nomeComune, nomeProvincia);
	}
	
	@GetMapping("/updatecomune")
	public String updateComune (@RequestParam String nomeComuneOld , @RequestParam String nomeComuneNew , @RequestParam String nomeProvincia) {
		return comuneService.updateComune(nomeComuneOld, nomeComuneNew, nomeProvincia);
	}
	
	
	@GetMapping ("/findComuneByNome")
	public Comune findByNome (@RequestParam String nomeComune){
		return comuneService.findByNome(nomeComune);
	}

}
