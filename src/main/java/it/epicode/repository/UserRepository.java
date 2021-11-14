package it.epicode.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.epicode.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.username =:username")
	List<User> myFindByUser(String username);

	Page<User> findAll (Pageable page);

	/* Sort */
	// Formula: findBy + OrderBy + NomeColonna + Ordinamento(Asc/Desc)
	List<User> findByOrderByUsernameDesc();

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);


}
