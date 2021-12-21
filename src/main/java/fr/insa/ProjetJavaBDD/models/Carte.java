package fr.insa.ProjetJavaBDD.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

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
	private int plafond;
	@Id
	@Size(min=16, max=16)
	private long numeroCarte;
	@EqualsAndHashCode.Include()
	private String motDePasse;
	@JsonIgnore
	@ManyToOne 
	private Compte compte;
}
