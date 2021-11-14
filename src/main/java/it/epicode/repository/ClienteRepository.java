package it.epicode.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Cliente findByRagioneSociale(String ragioneSociale);

	Cliente findByPartitaIva(String partitaIva);

	Cliente findByFatturatoAnnuale(int fatturato);

	Cliente findByDataInserimento(LocalDate dataInserimento);

	Cliente findByDataUltimoContatto(LocalDate DataUltimoContatto);

	/* Sort */
	// Formula: findBy + OrderBy + NomeColonna + Ordinamento(Asc/Desc)
	Page<Cliente> findByOrderByRagioneSociale(Pageable page);

	Page<Cliente> findByOrderByFatturatoAnnualeDesc(Pageable page);

	Page<Cliente> findByOrderByDataInserimento(Pageable page);

	Page<Cliente> findByOrderByDataUltimoContatto(Pageable page);

}
