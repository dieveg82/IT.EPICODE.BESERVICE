package it.epicode;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.epicode.controller.AuthController;
import it.epicode.model.Indirizzo;
import it.epicode.model.RoleType;
import it.epicode.model.TipoCliente;
import it.epicode.security.SignupRequest;
import it.epicode.service.ClienteService;
import it.epicode.service.ComuneService;
import it.epicode.service.FatturaService;
import it.epicode.service.IndirizzoService;
import it.epicode.service.ProvinciaService;
import it.epicode.service.RoleService;
import it.epicode.service.StatoFatturaService;

@Component
public class Run implements CommandLineRunner {

	@Autowired
	ClienteService clienteService;
	@Autowired
	ComuneService comuneService;
	@Autowired
	FatturaService fatturaService;
	@Autowired
	IndirizzoService indirizzoService;
	@Autowired
	ProvinciaService provinciaService;
	@Autowired
	StatoFatturaService statoFatturaService;
	@Autowired
	RoleService roleService;
	@Autowired 
	AuthController authContrller;
	
	
	@Override
	public void run(String... args) throws Exception {

		provinciaService.popolaTabella();
		comuneService.popolaTabella();
		Indirizzo indSedeLegale = new Indirizzo("via toscana", "50", "09025", null, comuneService.findByNome("Assemini") );
		Indirizzo indSedeB = new Indirizzo("via milano", "30", "09130", null, comuneService.findByNome("Elmas") );
		indirizzoService.insertIndirizzo(indSedeLegale);
		indirizzoService.insertIndirizzo(indSedeB);
		clienteService.insertCliente("Romana Market","12345678901", TipoCliente.SRL, "Paola", "Collu", "070/220022", "info.romanamarket.it", indSedeB, indSedeLegale, LocalDate.of(2021, 01, 15), 500000);
		fatturaService.insertFattura(LocalDate.of(2021, 8, 20), 1, 2021, 1200, "Da pagare", "12345678901");
		roleService.insertRole(RoleType.ROLE_ADMIN);
		roleService.insertRole(RoleType.ROLE_USER);
		authContrller.registerUser(new SignupRequest("username", "user@gmail.com", "1234", "nome", "cognome"));
	}

}
