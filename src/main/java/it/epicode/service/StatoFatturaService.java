package it.epicode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.model.StatoFattura;
import it.epicode.repository.StatoFatturaRepository;

@Service
public class StatoFatturaService {

	@Autowired
	StatoFatturaRepository statoFatturaRepository;

	public String insertStatoPost(StatoFattura stato) {
		if (statoFatturaRepository.findByNome(stato.getNome()) == null) {
			statoFatturaRepository.save(stato);
			return "Inserimento effettuato con successo";
		} else {
			return "Stato Fattura già presente in database";
		}
	}

	public String insertStato(String nome) {
		if (statoFatturaRepository.findByNome(nome) == null) {
			statoFatturaRepository.save(new StatoFattura(nome));
			return "Inserimento effettuato con successo";
		} else {
			return "Stato Fattura già presente in anagrafica";
		}
	}

	public String updateStato(StatoFattura stato) {
		if (statoFatturaRepository.findById(stato.getId()).isPresent() == true) {
			statoFatturaRepository.save(stato);
			return "Modifica effettuata con successo";
		} else {
			return "Lo stato che si vuole modificare non esiste in database";
		}
	}

	public String removeStato(StatoFattura stato) {
		statoFatturaRepository.delete(stato);
		return "Cancellazione avvenuta con successo";
	}

}
