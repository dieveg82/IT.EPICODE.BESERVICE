package it.epicode.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.model.Provincia;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

//	List<Provincia> findByNome (String nomeProvincia);
	
	Provincia findByNome (String nomeProvincia);
	
	Page<Provincia> findAll (Pageable page);
}
