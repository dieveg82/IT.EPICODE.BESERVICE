package it.epicode.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.model.Provincia;
import it.epicode.repository.ProvinciaRepository;
import it.epicode.util.ImportCsvDb;

@Service
public class ProvinciaService {

	@Autowired
	ProvinciaRepository provinciaRepository;

	public void popolaTabella() throws IOException {

		ImportCsvDb importazione = new ImportCsvDb();
		for (String s : importazione.stringFileProvincia()) {
			if (s != null) {
				String[] array = s.split(";");
				Provincia p = new Provincia();
				p.setId(null);
				p.setNome(array[1]);
				p.setSigla(array[0]);
				provinciaRepository.save(p);
			}
		}
	}

	public String insertProvincia(String nomeProvincia, String siglaProvincia) {
		if (provinciaRepository.findByNome(nomeProvincia) == null) {
			provinciaRepository.save(new Provincia(nomeProvincia, siglaProvincia));
			return "Inserimento Provincia effettuato con successo";
		} else {
			return "Provincia già presente in anagrafica";
		}
	}

	public String updateProvincia(String nomeProvinciaOld, String nomeProvinciaNew, String siglaProvincia) {

		Provincia provincia = provinciaRepository.findByNome(nomeProvinciaOld);
		provincia.setNome(nomeProvinciaNew);
		provincia.setSigla(siglaProvincia);
		provinciaRepository.save(provincia);
		return "Modifica Effettuata con Successo";
	}

	public String insertProvincia(Provincia provincia) {
		if (provinciaRepository.findByNome(provincia.getNome()) == null) {
			provinciaRepository.save(provincia);
			return "Provincia inserita con successo";
		} else {
			return "Provincia già presente in anagrafica";
		}
	}

	public String updateProvincia(Provincia provincia) {

		if (provinciaRepository.findById(provincia.getId()).isPresent() == true) {
			provinciaRepository.save(provincia);
			return "Modifica effettuata con successo";
		} else {
			return "La provincia che si vuole modificare non esiste in database";
		}
	}

	public String removeProvincia(Long id) {
		provinciaRepository.delete(provinciaRepository.getById(id));
		return "Cancellazione effettuata con successo";
	}

	public Page<Provincia> findAllPage(Pageable page) {

		return provinciaRepository.findAll(page);
	}

	public Provincia findByNome(String nomeProvincia) {

		return provinciaRepository.findByNome(nomeProvincia);
	}

}
