package fr.insa.ProjetJavaBDD.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Carte {
	private int Plafond;
	@Id
	private int NumeroCarte;
	private String MotDePasse;
	@ManyToOne 
	private Compte compte;
}
