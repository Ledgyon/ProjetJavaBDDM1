package fr.insa.ProjetJavaBDD.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

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
	private int Solde;
	@Id
	@Size(min=11, max=11)
	private long num_Compte;
	private String IBAN;
	@ManyToMany(mappedBy="comptes")
	private List<Client> clients;
	@OneToMany(mappedBy="compte")
	private List<Carte> cartes;
	@OneToMany(mappedBy="compte")
	private List<Transaction> transactions;
	@ManyToOne
	private Agence agence;
	public Compte(Agence agence) {
		super();
		this.agence = agence;
	}
	
	
}
