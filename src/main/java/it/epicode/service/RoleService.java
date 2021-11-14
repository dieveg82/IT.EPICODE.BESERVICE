package it.epicode.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.model.Role;
import it.epicode.model.RoleType;
import it.epicode.repository.RoleRepository;


@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	public String insertRole (RoleType role) {
		
		roleRepository.save(new Role (role));
		return "Role inserito con successo";
		
	}
	
	public Optional<Role> findByRoleType (RoleType roleType){
		return roleRepository.findByRoleType(roleType);
	}
}
