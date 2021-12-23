package fr.insa.ProjetJavaBDD.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Carte {
	private int plafond; // Plafond des dépenses d'une carte 
	@Id
	@Digits(integer=16,fraction=0)
	@NotNull
	private long numeroCarte; // Numéro d'identification de la carte sur 16 valeurs
	@EqualsAndHashCode.Include()
	private int motDePasse; // Mot de Passe relié à la carte
	@JsonIgnore
	@ManyToOne 
	private Compte compte; // Compte rataché à la carte d'où l'argent va être débité
}