package fr.insa.ProjetJavaBDD.ressouces.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CompteCreateModel implements Serializable{

	private long agenceCode;
	private List<Integer> idClient;
	private long numCompte;
	private int solde;

}
