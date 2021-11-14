package it.epicode.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.model.Cliente;
import it.epicode.model.Fattura;
import it.epicode.model.StatoFattura;

public interface FatturaRepository extends JpaRepository<Fattura, Long>{

	List<Fattura> findByNumeroFattura (int numeroFattura);
	List<Fattura> findByCliente (Cliente cliente);
	List<Fattura> findByStato (StatoFattura stato);
	List<Fattura> findByData (LocalDate data);
	List<Fattura> findByAnno (int anno);
	
	
	/*   LA QUERY HA QUESTA STRUTTURA MA NON ABBIAMO MAI USATO IL VALORE MAGGIORE E MINORE
	@Query("SELECT u FROM Fattura u WHERE u.importo >:importoMinino AND u.importo<:importoMassimo" )
	List<Fattura> findByRangeImporti (int importoMinino , int importMassimo);
	*/

}
