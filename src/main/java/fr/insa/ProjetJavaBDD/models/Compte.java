package fr.insa.ProjetJavaBDD.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Compte {
	private int Solde;
	@Id
	private String IBAN;
	@ManyToMany(mappedBy="comptes")
	private List<Client> clients;
	@OneToMany(mappedBy="compte")
	private List<Carte> cartes;
	@OneToMany(mappedBy="compte")
	private List<Transaction> transactions;
}
