package it.epicode.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.model.Comune;
import it.epicode.model.Provincia;

public interface ComuneRepository extends JpaRepository<Comune, Long> {

//	List<Comune> findByNome (String nomeComune);
	
	Comune findByNome (String nomeComune);
	
	Page<Comune> findAll (Pageable pageable);
}
