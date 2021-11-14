package it.epicode.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import it.epicode.model.Comune;
import it.epicode.model.User;
import it.epicode.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
    
    public boolean existsByUsername(String username) {
    	return userRepository.existsByUsername(username);
    }
    
    public boolean existsByEmail(String email) {
    	return userRepository.existsByEmail(email);
    }

	public void insertUser(User user) {
		userRepository.save(user);
		
	}

	public Page<User> findAllPage(Pageable page) {
		return userRepository.findAll(page);
	}




}
