package fr.insa.ProjetJavaBDD.ressouces.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgenceCreateModel implements Serializable{
	private int Code_agence;
	private String Adresse;
}
