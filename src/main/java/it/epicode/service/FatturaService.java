package it.epicode.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.model.Cliente;
import it.epicode.model.Fattura;
import it.epicode.model.StatoFattura;
import it.epicode.repository.ClienteRepository;
import it.epicode.repository.FatturaRepository;
import it.epicode.repository.StatoFatturaRepository;

@Service
public class FatturaService {

	@Autowired
	FatturaRepository fatturaRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	StatoFatturaRepository statoFatturaRepository;

	public String insertFattura(LocalDate data, int numeroFattura, int anno, int importo, String statoFattura,
			String partitaIva) {
		Cliente cliente = clienteRepository.findByPartitaIva(partitaIva);
		if (clienteRepository.findByPartitaIva(cliente.getPartitaIva()) == null) {
			return "Impossibile inserire fattura cliente non presente in anagrafica";
		} else {
			StatoFattura stato = new StatoFattura(statoFattura);
			List<Fattura> listFattura = fatturaRepository.findByNumeroFattura(numeroFattura);
			for (Fattura f : listFattura) {
				if (f.getCliente().equals(cliente)) {
					return "Impossibile inserire una fattura con lo stesso numero per lo stesso cliente ";
				}
			}
			if (statoFatturaRepository.findByNome(statoFattura) == null) {
				statoFatturaRepository.save(stato);
			}
			fatturaRepository.save(new Fattura(data, numeroFattura, anno, importo, stato, cliente));
			return "Inserimento fattura effettuato con successo";
		}
	}

	public String insertFattura(Fattura fattura) {
		Cliente cliente = clienteRepository.findByPartitaIva(fattura.getCliente().getPartitaIva());
		if (cliente == null) {
			clienteRepository.save(fattura.getCliente());
			System.out.println("Cliente non presente in anagrafica aggiunto automaticamente");
		}
		StatoFattura stato = statoFatturaRepository.findByNome(fattura.getStato().getNome());
		List<Fattura> listFattura = fatturaRepository.findByNumeroFattura(fattura.getNumeroFattura());
		for (Fattura f : listFattura) {
			if (f.getCliente().equals(cliente)) {
				return "Impossibile inserire una fattura con lo stesso numero per lo stesso cliente ";
			}
		}
		if (!statoFatturaRepository.findByNome(fattura.getStato().getNome()).equals(stato)) {
			statoFatturaRepository.save(stato);
		}
		fatturaRepository.save(fattura);
		return "Fattura inserita con successo";
	}

	public String updateFattura(Fattura fattura, Long id) {
		Fattura fatturaNew = fattura;
		fatturaNew.setId(id);
		fatturaRepository.save(fatturaNew);
		return "Fattura inserita con successo";
	}

	public String removeFattura(Fattura fattura) {
		fatturaRepository.delete(fattura);
		return "Rimozione fattura effettuata con successo";
	}

	public List<Fattura> findByNumeroFattura(int numeroFattura) {
		return fatturaRepository.findByNumeroFattura(numeroFattura);
	}

	public List<Fattura> findByCliente(String ragioneSociale) {
		Cliente cliente = (Cliente) clienteRepository.findByRagioneSociale(ragioneSociale);
		return fatturaRepository.findByCliente(cliente);
	}

	public List<Fattura> findByStato(String stato) {
		return fatturaRepository.findByStato(statoFatturaRepository.findByNome(stato));
	}

	public List<Fattura> findByData(LocalDate data) {
		return fatturaRepository.findByData(data);
	}

	public List<Fattura> findByAnno(int anno) {
		return fatturaRepository.findByAnno(anno);
	}

}
