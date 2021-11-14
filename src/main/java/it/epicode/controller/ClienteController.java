package it.epicode.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.model.Cliente;
import it.epicode.model.Indirizzo;
import it.epicode.model.TipoCliente;
import it.epicode.service.ClienteService;

@RestController
@RequestMapping("/controllercliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@PostMapping("/insertclientepost")
	public String insertCliente(@RequestBody Cliente cliente) {
		return clienteService.insertCliente(cliente);
	}

	@PostMapping("/updatecliente")
	public String updateCliente(@RequestBody Cliente cliente) {
		return clienteService.updateCliente(cliente);
	}

	@GetMapping("/removecliente")
	public String removeCliente(@RequestParam String partitaIva) {
		return clienteService.removeCliente(partitaIva);
	}

	@GetMapping("/findbypartitaiva")
	public Cliente findByPartitaIva(@RequestParam String partitaIva) {
		return clienteService.findByPartitaIva(partitaIva);
	}

	@GetMapping("/findbyfatturatoannuale")
	public Cliente findByFatturatoAnnuale(@RequestParam int fatturatoAnnuale) {
		return clienteService.findByFatturatoAnnuale(fatturatoAnnuale);
	}

	@GetMapping("/findbydatainserimento")
	public Cliente findByDataInserimento(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInserimento) {
		return clienteService.findByDataInserimento(dataInserimento);
	}

	@GetMapping("/findbydataultimocontatto")
	public Cliente findByDataUltimoContatto(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataUltimoContatto) {
		return clienteService.findByDataInserimento(dataUltimoContatto);
	}

	@GetMapping("/findallorderagsociale")
	public Page<Cliente> findAllRagSociale(Pageable page) {
		return clienteService.findAllOrderByRagioneSociale(page);
	}

	@GetMapping("/findallorderfattannuale")
	public Page<Cliente> findAllFattAnnuale(Pageable page) {
		return clienteService.findAllOrderByFatturatoAnnuale(page);
	}

	@GetMapping("/findallorderdatainser")
	public Page<Cliente> findAllDataInserimento(Pageable page) {
		return clienteService.findAllOrderByDataInserimento(page);
	}

	@GetMapping("/findallorderdataultcon")
	public Page<Cliente> findAllDataUltContatto(Pageable page) {
		return clienteService.findAllOrderByDataUltimoContatto(page);
	}

}
