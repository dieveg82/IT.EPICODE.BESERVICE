package it.epicode.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column (unique = true ,nullable = false)
	private String ragioneSociale;
	@Column(unique = true , nullable = false)
	private String partitaIva;
	@Column ( nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;
	private String nomeContatto;
	private String cognomeContatto;
	private String telefonoContatto;
	private String emailContatto;	
	@OneToOne
	private Indirizzo indirizzoSedeOperativo;	
	@OneToOne
	private Indirizzo indirizzoSedeLegale;
	private LocalDate dataInserimento;
	private LocalDate dataUltimoContatto;
	private int fatturatoAnnuale;
	

	public Cliente(String ragioneSociale, String partitaIva, TipoCliente tipoCliente, String nomeContatto,
			String cognomeContatto, String telefonoContatto, String emailContatto, Indirizzo indirizzoSedeOperativo,
			Indirizzo indirizzoSedeLegale, LocalDate dataInserimento,
			int fatturatoAnnuale) {

		this.ragioneSociale = ragioneSociale;
		this.partitaIva = partitaIva;
		this.tipoCliente = tipoCliente;
		this.nomeContatto = nomeContatto;
		this.cognomeContatto = cognomeContatto;
		this.telefonoContatto = telefonoContatto;
		this.emailContatto = emailContatto;
		this.indirizzoSedeOperativo = indirizzoSedeOperativo;
		this.indirizzoSedeLegale = indirizzoSedeLegale;
		this.dataInserimento = dataInserimento;
		this.fatturatoAnnuale = fatturatoAnnuale;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", ragioneSociale=" + ragioneSociale + ", partitaIva=" + partitaIva
				+ ", tipoCliente=" + tipoCliente + ", nomeContatto=" + nomeContatto + ", cognomeContatto="
				+ cognomeContatto + ", telefonoContatto=" + telefonoContatto + ", emailContatto=" + emailContatto
				+ ", indirizzoSedeOperativo=" + indirizzoSedeOperativo + ", indirizzoSedeLegale=" + indirizzoSedeLegale
				+ ", dataInserimento=" + dataInserimento + ", dataUltimoContatto=" + dataUltimoContatto
				+ ", fatturatoAnnuale=" + fatturatoAnnuale + "]";
	}

	public Cliente(String ragioneSociale2, String partitaIva2, TipoCliente tipo, String nomeContatto2,
			String cognomeContatto2, String telefonoContatto2, String emailContatto2, LocalDate dataInserimento2,
			int fatturatoAnnuale2) {
		
		this.ragioneSociale = ragioneSociale;
		this.partitaIva = partitaIva;
		this.tipoCliente = tipoCliente;
		this.nomeContatto = nomeContatto;
		this.cognomeContatto = cognomeContatto;
		this.telefonoContatto = telefonoContatto;
		this.emailContatto = emailContatto;
		this.dataInserimento = dataInserimento;
		this.fatturatoAnnuale = fatturatoAnnuale;
	}

}
