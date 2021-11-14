package it.epicode.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import it.epicode.util.StringAttributeConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true , nullable = false)
    private String username;
    private String nome;
    private String cognome;
    /* Una volta definito il converter, è possibile annotare le proprietà che si intende cifrare, 
    in modo che il motore di persistenza JPA possa effettuare le operazioni di conversione */
    @Column (unique =true , nullable = false)
    @Convert(converter = StringAttributeConverter.class)
    private String email;  
    @Column (nullable=false)
    private String password;    
    @ManyToMany
    @JoinTable(    name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    
    //@Email
    public User(Long id, String username, String email, String password, String nome, String cognome) {
          super();
          this.id = id;
          this.username = username;
          this.email = email;
          this.password = password;
          this.nome = nome;
          this.cognome = cognome;
      }
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", nome=" + nome + ", email=" + email + ", password="
				+ password + " ]";
	}

	
	
}
