package fr.insa.ProjetJavaBDD.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

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
	private int solde; // Argent actuellement disponible sur un compte
	@Id
	@Digits(integer=11,fraction=0)
	@NotNull
	private long numCompte; // ID d'un compte sur 11 valeurs
	private String iban; // IBAN du compte
	
	@ManyToMany(mappedBy="comptes")
	private List<Client> clients; // Liste des Clients Propriétaires
	
	@OneToMany(mappedBy="compte")
	private List<Carte> cartes; // Liste de Cartes rattachée au compte
	@JsonIgnore
	@OneToMany(mappedBy="compteEmetteur")
	private List<Transaction> transactions; // Liste des Transactions faite à partir du compte
	
	@ManyToOne
	private Agence agence; // Référence à l'agence du compte
	
}
