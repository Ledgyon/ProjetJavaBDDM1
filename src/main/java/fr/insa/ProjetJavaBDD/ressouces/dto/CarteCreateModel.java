package fr.insa.ProjetJavaBDD.ressouces.dto;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CarteCreateModel implements Serializable {
	private int Plafond;
	private int NumeroCarte;
	private String MotDePasse;
}
