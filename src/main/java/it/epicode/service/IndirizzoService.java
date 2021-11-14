package it.epicode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.model.Comune;
import it.epicode.model.Indirizzo;
import it.epicode.repository.ComuneRepository;
import it.epicode.repository.IndirizzoRepository;

@Service
public class IndirizzoService {

	@Autowired
	IndirizzoRepository indirizzoRepository;
	@Autowired
	ComuneRepository comuneRepository;

	public String insertIndirizzo(String via, String civico, String cap, String località, String comune) {
		Comune comuneNew = comuneRepository.findByNome(comune);
		indirizzoRepository.save(new Indirizzo(via, civico, cap, località, comuneNew));
		return "Indirizzo inserito con successo";
	}

	public String insertIndirizzo(Indirizzo indirizzo) {
		if (indirizzoRepository.findbyIndirizzoViaCap(indirizzo.getVia(), indirizzo.getCivico(),
				indirizzo.getCap()) == null) {
			indirizzoRepository.save(indirizzo);
			return "Indirizzo inserito con successo";
		} else {
			return "Indirizzo già presente in anagrafica";
		}
	}

	public String updateIndirizzo(Indirizzo indirizzo) {
		if (indirizzoRepository.findById(indirizzo.getId()).isPresent() == true) {
			indirizzoRepository.save(indirizzo);
			return "Modifica effettuata con successo";
		} else {
			return "L'indirizzo che si vuole modificare non esiste in database";
		}
	}

	public String removeIndirizzo(Long id) {
		indirizzoRepository.delete(indirizzoRepository.getById(id));
		return "Cliente rimosso con successo";
	}

	public Indirizzo findByViaByCivicoByCap(String via, String civico, String cap) {
		return indirizzoRepository.findbyIndirizzoViaCap(via, civico, cap);
	}

}
