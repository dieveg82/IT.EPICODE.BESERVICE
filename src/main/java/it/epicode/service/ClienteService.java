package it.epicode.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.model.Cliente;
import it.epicode.model.Indirizzo;
import it.epicode.model.TipoCliente;
import it.epicode.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public String insertCliente(Cliente cliente) {
		if (clienteRepository.findByPartitaIva(cliente.getPartitaIva()) == null) {
		clienteRepository.save(cliente);
		return "Cliente inserito correttamente"; }
		else {
			return "La seguente Partita Iva risulta già presente in DataBase";
		}
	}

	public String updateCliente(Cliente cliente) {
		if (clienteRepository.findById(cliente.getId()).isPresent() == true) {
			clienteRepository.save(cliente);
			return "Modifica effettuata con successo";
		} else {
			return "Il cliente che si vuole modificare non esiste in database";
		}
	}

	// METEDI GET PER SCOPO DIDATTICO
	public String insertCliente(String ragioneSociale, String partitaIva, TipoCliente tipo, String nomeContatto,
			String cognomeContatto, String telefonoContatto, String emailContatto, Indirizzo indSedeB,
			Indirizzo indSedeLegale, LocalDate dataInserimento, int fatturatoAnnuale) {

		clienteRepository.save(new Cliente(ragioneSociale, partitaIva, tipo, nomeContatto, cognomeContatto,
				telefonoContatto, emailContatto, indSedeB, indSedeLegale, dataInserimento, fatturatoAnnuale));
		return "Cliente inserito con successo ";
	}

	public String removeCliente(String partitaIva) {
		clienteRepository.delete((Cliente) clienteRepository.findByPartitaIva(partitaIva));
		return "Cliente rimosso con successo";
	}

	public Cliente findByPartitaIva(String partitaIva) {
		return clienteRepository.findByPartitaIva(partitaIva);
	}

	public Cliente findByFatturatoAnnuale(int fatturato) {
		return clienteRepository.findByFatturatoAnnuale(fatturato);
	}

	public Cliente findByDataInserimento(LocalDate data) {
		return clienteRepository.findByDataInserimento(data);
	}

	public Cliente findByDataUltimoContatto(LocalDate data) {
		return clienteRepository.findByDataUltimoContatto(data);
	}

	public Page<Cliente> findAllOrderByRagioneSociale(Pageable page) {
		return clienteRepository.findByOrderByRagioneSociale(page);
	}

	public Page<Cliente> findAllOrderByFatturatoAnnuale(Pageable page) {
		return clienteRepository.findByOrderByFatturatoAnnualeDesc(page);
	}

	public Page<Cliente> findAllOrderByDataInserimento(Pageable page) {
		return clienteRepository.findByOrderByDataInserimento(page);
	}

	public Page<Cliente> findAllOrderByDataUltimoContatto(Pageable page) {
		return clienteRepository.findByOrderByDataUltimoContatto(page);
	}

}
