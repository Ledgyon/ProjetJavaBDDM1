package fr.insa.ProjetJavaBDD.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Agence {
	@Id
	@Digits(integer=5,fraction=0)
	@NotNull
	private long codeAgence; // Code de l'agence sur 5 valeurs numériques
	private String adresse; // Adresse de l'agence
	@JsonIgnore
	@OneToMany(mappedBy="agence")
	private List<Client> listeClient; // Liste de Clients qui est rattachée à cette agence
	@JsonIgnore
	@OneToMany(mappedBy="agence")
	private List<Compte> listeCompte; // Liste de Comptes fournie par l'agence
}
