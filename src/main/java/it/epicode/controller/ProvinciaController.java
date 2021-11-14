package it.epicode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.model.Provincia;
import it.epicode.service.ProvinciaService;

@RestController
@RequestMapping("/controllerprovincia")
public class ProvinciaController {

	@Autowired
	ProvinciaService provinciaService;

	@PostMapping("/insertprovinciapost")
	public String insertProvincia(@RequestBody Provincia provincia) {
		return provinciaService.insertProvincia(provincia);
	}

	@PostMapping("/updateprovinciapost")
	public String updateProvincia(@RequestBody Provincia provincia) {
		return provinciaService.updateProvincia(provincia);
	}

	@GetMapping("/removeprovincia")
	public String removeProvincia(@RequestParam Long id) {
		return provinciaService.removeProvincia(id);
	}

	@GetMapping("/insertprovincia")
	public String insertProvincia(@RequestParam String nomeProvincia, @RequestParam String siglaProvincia) {
		return provinciaService.insertProvincia(nomeProvincia, siglaProvincia);
	}

	@GetMapping("/updateprovincia")
	public String updateProvincia(@RequestParam String nomeProvinciaOld, @RequestParam String nomeProvinciaNew,
			@RequestParam String siglaProvincia) {
		return provinciaService.updateProvincia(nomeProvinciaOld, nomeProvinciaNew, siglaProvincia);
	}

	@GetMapping("/findallpage")
	public Page<Provincia> findAllPage(Pageable page) {
		return provinciaService.findAllPage(page);
	}

	@GetMapping("/findprovinciapage")
	public ResponseEntity<Page<Provincia>> getAllProvincia(Pageable page) {
		Page<Provincia> findAll = provinciaService.findAllPage(page);
		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/findprovinciabynome")
	public Provincia findByNome(@RequestParam String nomeProvincia) {
		return provinciaService.findByNome(nomeProvincia);
	}

}
