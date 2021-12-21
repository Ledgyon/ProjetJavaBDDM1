package fr.insa.ProjetJavaBDD.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id; // Id auto-généré pou run client
	private String nom; // Nom du client
	private String prenom; //Prenom du client
	private int age; // Age du client
	private int telephone; // Téléphone du client
	private String adresse; // Adresse du client
	@ManyToOne 
	@JsonIgnore
	private Agence agence; // Agence liée au client
	@ManyToMany
	@JsonIgnore
	@JoinTable(name= "client_compte",
			joinColumns = { @JoinColumn(name="client_id")},
			inverseJoinColumns = { @JoinColumn(name = "compte_id")})
	private List<Compte> comptes; // Liste de Compte du client
}
