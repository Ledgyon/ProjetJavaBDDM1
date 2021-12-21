package fr.insa.ProjetJavaBDD.ressouces.dto;


import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


//Création d'un model avec les attributs nécessaires (connus à l'avance) 
@Getter
@Setter
public class TransactionCreateModel implements Serializable{

	private int montantTransac;
	private long numCompte;
}
