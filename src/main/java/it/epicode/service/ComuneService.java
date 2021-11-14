package it.epicode.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.model.Cliente;
import it.epicode.model.Comune;
import it.epicode.model.Provincia;
import it.epicode.repository.ComuneRepository;
import it.epicode.repository.ProvinciaRepository;
import it.epicode.util.ImportCsvDb;

@Service
public class ComuneService {

	@Autowired
	ComuneRepository comuneRepository;
	@Autowired
	ProvinciaRepository provinciaRepository;

	public void popolaTabella() throws IOException {

		ImportCsvDb importazione = new ImportCsvDb();
		for (String s : importazione.stringFileComune()) {
			if (s != null) {
				String[] array = s.split(";");
				Comune c = new Comune();
				c.setId(null);
				c.setNome(array[2]);
				Provincia provincia = provinciaRepository.findByNome(array[3]);
				if (provincia != null) {
					c.setProvincia(provincia);
				} else
					c.setProvincia(null);
				comuneRepository.save(c);
			}
		}

	}

	public String inserimentoComune(String nomeComune, String nomeProvincia) {
		Comune comune = new Comune();
		Provincia provincia = provinciaRepository.findByNome(nomeProvincia);
		if (provincia == null) {
			return "Impossibile inserire il comune , provincia non presente in database ";
		} else {
			comune.setProvincia(provincia);
			comune.setNome(nomeComune);
			comuneRepository.save(comune);
			return "Inserimento eseguito con successo";
		}
	}

	public String updateComune(String nomeComuneOld, String nomeComuneNew, String nomeProvincia) {
		Comune comuneMod = comuneRepository.findByNome(nomeComuneOld);
		comuneMod.setNome(nomeComuneNew);
		Provincia provincia = provinciaRepository.findByNome(nomeProvincia);
		if (provincia == null) {
			return "Impossibile modificare comune , provincia non presente in database";
		} else
			comuneMod.setProvincia(provincia);
		comuneRepository.save(comuneMod);
		return "Modifica effettuata con Successo";

	}

	public String inserimentoComunePost(Comune comune) {

		if (comuneRepository.findByNome(comune.getNome()) == null) {
			Provincia provincia = provinciaRepository.findByNome(comune.getProvincia().getNome());
			if (provincia == null) {
				return "Impossibile inserire il comune , provincia non presente in database ";
			} else {
				comune.setProvincia(provincia);
				comuneRepository.save(comune);
				return "Inserimento eseguito con successo";
			}
		} else {
			return "Comune già esistente in Database";
		}
	}

	public String updateComune(Comune comune) {
		if (comuneRepository.findById(comune.getId()).isPresent() == true) {
			comuneRepository.save(comune);
			return "Modifica effettuata con successo";
		} else {
			return "Il comune che si vuole modificare non esiste in database";
		}

	}

	public String removeComune(Long id) {
		comuneRepository.delete(comuneRepository.getById(id));
		return "Cancellazione avvenuta con successo";
	}

	public String removeComune(Comune comune) {

		comuneRepository.delete(comune);
		return "Cancellazione avvenuta con successo";
	}

	public Comune findByNome(String nomeComune) {
		Comune comune = comuneRepository.findByNome(nomeComune);
		return comune;
	}

	public Page<Comune> findAll(Pageable pageable) {
		return comuneRepository.findAll(pageable);
	}

}
