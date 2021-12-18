package fr.insa.ProjetJavaBDD.ressouces.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgenceReponseModel {

	private int Code_agence;
	private String Adresse;	
	
}
