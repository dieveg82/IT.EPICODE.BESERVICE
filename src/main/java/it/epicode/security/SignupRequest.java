package it.epicode.security;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class SignupRequest {
    private String username;
   // @Email
    private String email;
    private Set<String> role;
    private String password;
    private String nome;
    private String cognome;
	
    public SignupRequest(String username, String email, String password, String nome, String cognome) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
	}


    
    
    
}
