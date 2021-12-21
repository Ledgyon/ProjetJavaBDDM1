package fr.insa.ProjetJavaBDD.ressouces.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientCreateModel implements Serializable{
	private String nom;
	private String prenom;
	private int age;
	private int telephone;
	private String adresse;
	private long agenceId;
}
