package it.epicode.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.model.Cliente;
import it.epicode.model.StatoFattura;

public interface StatoFatturaRepository extends JpaRepository<StatoFattura, Long> {

	StatoFattura findByNome (String nome);
}
