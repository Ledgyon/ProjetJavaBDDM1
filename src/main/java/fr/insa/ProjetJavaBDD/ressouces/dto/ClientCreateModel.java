package fr.insa.ProjetJavaBDD.ressouces.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


//Création d'un model avec les attributs nécessaires (connus à l'avance) 
@Getter
@Setter
public class ClientCreateModel implements Serializable{
	private String nom;
	private String prenom;
	private int age;
	private long telephone;
	private String adresse;
	private long agenceId;
}
