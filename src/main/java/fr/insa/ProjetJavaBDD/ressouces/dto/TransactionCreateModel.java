package fr.insa.ProjetJavaBDD.ressouces.dto;


import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TransactionCreateModel implements Serializable{

	private int montantTransac;
	private long numCompte;
}
