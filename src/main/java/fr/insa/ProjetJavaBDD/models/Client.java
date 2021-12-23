package fr.insa.ProjetJavaBDD.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	private int id; // Id auto-généré pou run client
	private String nom; // Nom du client
	private String prenom; //Prenom du client
	private int age; // Age du client
	private long telephone; // Téléphone du client
	private String adresse; // Adresse du client
	@JsonIgnore
	@ManyToOne 
	private Agence agence; // Agence liée au client
	@JsonIgnore
	@ManyToMany(mappedBy="clients")
	private List<Compte> comptes; // Liste de Compte du client
}
