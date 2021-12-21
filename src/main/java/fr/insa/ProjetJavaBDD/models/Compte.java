package fr.insa.ProjetJavaBDD.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Compte {
	private int solde;
	@Id
	@Size(min=11, max=11)
	private long numCompte;
	private String iban;
	@JsonIgnore
	@ManyToMany(mappedBy="comptes")
	private List<Client> clients;
	@JsonIgnore
	@OneToMany(mappedBy="compte")
	private List<Carte> cartes;
	@JsonIgnore
	@OneToMany(mappedBy="compte")
	private List<Transaction> transactions;
	@JsonIgnore
	@ManyToOne
	private Agence agence;
	public Compte(Agence agence) {
		super();
		this.agence = agence;
	}
	
	
}
