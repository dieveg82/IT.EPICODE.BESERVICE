package it.epicode.model;

import java.time.LocalDate;

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
@Getter
@Setter
@Entity
public class Fattura {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private LocalDate data;
	@Column ( nullable = false)
	private int numeroFattura;
	@Column(nullable = false)
	private int anno;
	@Column ( nullable = false)
	private int importo;
	@OneToOne
	private StatoFattura stato;
	@ManyToOne ()
	private Cliente cliente;
	
	public Fattura (LocalDate data , int numeroFattura , int anno , int importo , StatoFattura stato , Cliente cliente) {
		
		this.data = data;
		this.numeroFattura = numeroFattura;
		this.anno = anno;
		this.importo = importo;
		this.stato = stato;
		this.cliente = cliente;
		
		
	}
	
}
