package fr.insa.ProjetJavaBDD.ressouces.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Récupération d'un model de réponse pour l'affichage avec les attributs voulus
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgenceReponseModel {

	private long codeAgence;
	private String adresse;	
	
}
