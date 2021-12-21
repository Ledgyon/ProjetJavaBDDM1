package fr.insa.ProjetJavaBDD.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

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
public class Agence {
	@Id
	@Size(min=5, max=5)
	private long codeAgence; // Code de l'agence sur 5 Valeurs
	private String adresse; // Adresse de l'agence
	@JsonIgnore
	@OneToMany(mappedBy="agence")
	private List<Client> listeClient; // Liste de Clients dont l'agence est composée
	@JsonIgnore
	@OneToMany(mappedBy="agence")
	private List<Compte> listeCompte; // Liste de Comptes que l'agence fournie aux clients
}
