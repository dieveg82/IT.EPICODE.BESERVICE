package it.epicode.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.model.Comune;
import it.epicode.model.Indirizzo;

public interface IndirizzoRepository extends JpaRepository<Indirizzo, Long> {

	@Query("SELECT u FROM Indirizzo u WHERE u.via = :via AND u.civico = :civico AND u.cap = :cap")
	Indirizzo findbyIndirizzoViaCap(String via, String civico, String cap);
}
