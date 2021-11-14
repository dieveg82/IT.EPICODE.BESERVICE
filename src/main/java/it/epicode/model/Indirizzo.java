package it.epicode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Indirizzo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String via;
	@Column (nullable = false)
	private String civico;
	@Column (nullable = false)
	private String cap;
	private String località;
	@ManyToOne
	private Comune comune;
	
	
	public Indirizzo( String via, String civico, String cap, String località, Comune comune) {
		this.via = via;
		this.civico = civico;
		this.cap = cap;
		this.località = località;
		this.comune = comune;
	}
	
	
	
}
