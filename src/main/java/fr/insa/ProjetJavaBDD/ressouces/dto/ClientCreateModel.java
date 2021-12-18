package fr.insa.ProjetJavaBDD.ressouces.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientCreateModel implements Serializable{
	private String Nom;
	private String Prenom;
	private int Age;
	private int Telephone;
	private String Adresse;
	private int agenceId;
}
